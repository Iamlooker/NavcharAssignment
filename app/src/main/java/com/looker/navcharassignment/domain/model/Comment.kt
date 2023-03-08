package com.looker.navcharassignment.domain.model

@kotlinx.serialization.Serializable
data class Comment(
	val user: User,
	val text: String,
	val commentTime: Long,
	val isLiked: Boolean = false
)
