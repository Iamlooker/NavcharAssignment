package com.looker.navcharassignment.domain.repository

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.looker.navcharassignment.domain.model.Comment
import com.looker.navcharassignment.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface FeedRepository {

	fun getPostsPaginated(): Flow<PagingData<Post>>

	fun getPostStream(): Flow<List<Post>>

	fun getPost(postId: Long): Flow<Post>

	fun getComments(postId: Long): Flow<List<Comment>>

	suspend fun likePost(postId: Long)

	// Returns true if successful and false otherwise
	suspend fun syncRemote(): Boolean

}