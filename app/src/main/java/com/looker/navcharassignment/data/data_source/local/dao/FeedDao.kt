package com.looker.navcharassignment.data.data_source.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.looker.navcharassignment.domain.model.Post
import kotlinx.coroutines.flow.Flow

@Dao
interface FeedDao {

	@Query("SELECT * FROM post_table")
	fun postsStreamPaginated(): PagingSource<Int, Post>

	@Query("SELECT * FROM post_table")
	fun postsStream(): Flow<List<Post>>

	@Query("SELECT * FROM post_table WHERE id = :postId")
	fun getPost(postId: Long): Flow<Post>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertPost(post: Post)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertPosts(posts: List<Post>)

	@Update(onConflict = OnConflictStrategy.REPLACE)
	suspend fun updatePost(post: Post)

	@Query("DELETE FROM post_table")
	suspend fun clearAllPosts()

}