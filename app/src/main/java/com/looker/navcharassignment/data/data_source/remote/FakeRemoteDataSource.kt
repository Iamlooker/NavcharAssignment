package com.looker.navcharassignment.data.data_source.remote

import com.looker.navcharassignment.domain.model.Category
import com.looker.navcharassignment.domain.model.Comment
import com.looker.navcharassignment.domain.model.Post
import com.looker.navcharassignment.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.random.Random
import kotlin.random.nextInt

object FakeRemoteDataSource {

	private val single = listOf(
		"https://images.unsplash.com/photo-1677523875054-a615d799d9c8?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDE2fHhqUFI0aGxrQkdBfHxlbnwwfHx8fA%3D%3D&auto=format&w=500&q=30"
	)

	private val images = listOf(
		"https://images.unsplash.com/photo-1677843019211-0c1ba7e1f377?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDJ8aVVJc25WdGpCMFl8fGVufDB8fHx8&auto=format&w=500&q=30",
		"https://images.unsplash.com/photo-1673767645452-e1a0a19c63c8?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDN8aVVJc25WdGpCMFl8fGVufDB8fHx8&auto=format&w=500&q=30",
		"https://images.unsplash.com/photo-1677941931332-3bcf18b0a1ee?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDF8aVVJc25WdGpCMFl8fGVufDB8fHx8&auto=format&w=500&q=30"
	)

	private fun createFakeComments(): List<Comment> = mutableListOf<Comment>().apply {
		repeat(Random.nextInt(12..24)) {
			add(
				Comment(
					user = User.DEFAULT,
					text = "The quick brown fox jumps over the lazy dog",
					commentTime = System.currentTimeMillis()
				)
			)
		}
	}

	// Simulates different content format
	private fun getImages(index: Int): List<String> = if (index % 2 == 0) images
	else if (index % 3 == 0) single
	else emptyList()

	private fun getTitle(index: Int): String = if (index % 2 == 0) "I created the following items."
	else if (index % 3 == 0) "This product is really great"
	else "The quick brown fox jumps over the lazy dog"

	private fun createNewPost(page: Int, index: Int) = Post(
		user = User.DEFAULT,
		category = Category.Question,
		postTime = System.currentTimeMillis() - 4_00_000L,
		title = getTitle(index),
		likes = index * 12,
		images = getImages(index),
		comments = createFakeComments()
	)

	const val PAGE_SIZE = 10
	private const val NUMBER_OF_PAGES = 10

	// Simulates call to a server
	suspend fun getItems(page: Int): List<Post> =
		withContext(Dispatchers.IO) {
			delay(2000)
			val startingPage = page + 1
			if (startingPage <= NUMBER_OF_PAGES) {
				(0 until PAGE_SIZE).map {
					delay(5)
					createNewPost(page, it)
				}
			} else emptyList()
		}
}