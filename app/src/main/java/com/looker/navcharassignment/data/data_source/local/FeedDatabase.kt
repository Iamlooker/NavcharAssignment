package com.looker.navcharassignment.data.data_source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.looker.navcharassignment.data.data_source.local.dao.FeedDao
import com.looker.navcharassignment.data.data_source.local.dao.RemoteKeysDao
import com.looker.navcharassignment.data.data_source.local.model.RemoteKeys
import com.looker.navcharassignment.data.data_source.local.util.CategoryConverters
import com.looker.navcharassignment.data.data_source.local.util.CommentsConverters
import com.looker.navcharassignment.data.data_source.local.util.ImagesConverters
import com.looker.navcharassignment.data.data_source.local.util.UserConverters
import com.looker.navcharassignment.domain.model.Post

@Database(
	entities = [Post::class, RemoteKeys::class],
	version = 1
)
@TypeConverters(
	CommentsConverters::class,
	CategoryConverters::class,
	UserConverters::class,
	ImagesConverters::class
)
abstract class FeedDatabase : RoomDatabase() {

	abstract val feedDao: FeedDao
	abstract val remoteKeyDao: RemoteKeysDao

	companion object {
		private const val NAME = "feed_database"

		fun createDatabase(context: Context): FeedDatabase = Room.databaseBuilder(
			context,
			FeedDatabase::class.java,
			NAME
		).build()
	}
}