package com.looker.navcharassignment.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import com.looker.navcharassignment.data.data_source.local.FeedDatabase
import com.looker.navcharassignment.data.data_source.local.dao.FeedDao
import com.looker.navcharassignment.data.data_source.local.dao.RemoteKeysDao
import com.looker.navcharassignment.data.data_source.remote.FakeRemoteDataSource
import com.looker.navcharassignment.data.repository.OfflineFirstFeedRepository
import com.looker.navcharassignment.domain.repository.FeedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

	@Provides
	fun providesDatabase(
		@ApplicationContext context: Context
	): FeedDatabase = FeedDatabase.createDatabase(context)

	@Provides
	fun providesFeedDao(
		db: FeedDatabase
	): FeedDao = db.feedDao

	@Provides
	fun providesRemoteKeyDao(
		db: FeedDatabase
	): RemoteKeysDao = db.remoteKeyDao

	@Provides
	fun provideRemoteDataSource(): FakeRemoteDataSource = FakeRemoteDataSource


	@Provides
	fun provideFeedRepository(
		database: FeedDatabase
	): FeedRepository = OfflineFirstFeedRepository(database)

}