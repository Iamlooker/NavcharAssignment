package com.looker.navcharassignment.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.random.Random

@Entity(tableName = "post_table")
@kotlinx.serialization.Serializable
data class Post(
	val user: User,
	val category: Category,
	val postTime: Long,
	val title: String,
	val likes: Int,
	val isLiked: Boolean = false,
	val images: List<String> = emptyList(),
	val comments: List<Comment> = emptyList(),
	@PrimaryKey
	val id: Long? = null
) {
	companion object {
		val DEFAULT = Post(
			user = User.DEFAULT,
			category = Category.Question,
			postTime = -1,
			title = "",
			likes = 0
		)
	}
}
