package com.looker.navcharassignment.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.looker.navcharassignment.data.data_source.FeedRemoteMediator
import com.looker.navcharassignment.data.data_source.local.FeedDatabase
import com.looker.navcharassignment.data.data_source.remote.FakeRemoteDataSource
import com.looker.navcharassignment.domain.model.Comment
import com.looker.navcharassignment.domain.model.Post
import com.looker.navcharassignment.domain.repository.FeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class OfflineFirstFeedRepository
@Inject constructor(
	private val database: FeedDatabase
) : FeedRepository {

	override fun getPostsPaginated(): Flow<PagingData<Post>> = Pager(
		config = PagingConfig(FakeRemoteDataSource.PAGE_SIZE),
		pagingSourceFactory = { database.feedDao.postsStreamPaginated() },
		remoteMediator = FeedRemoteMediator(database)
	).flow

	override fun getPostStream(): Flow<List<Post>> = database.feedDao.postsStream()

	override fun getPost(postId: Long): Flow<Post> = database.feedDao.getPost(postId)

	override fun getComments(postId: Long): Flow<List<Comment>> = database.feedDao.getPost(postId)
		.map { it.comments }

	// Can be replaced with a POST call to server
	override suspend fun likePost(postId: Long) {

	}

	// Simulates GET call from server
	override suspend fun syncRemote(): Boolean {
		// Because its always true for this case
		return true
	}
}