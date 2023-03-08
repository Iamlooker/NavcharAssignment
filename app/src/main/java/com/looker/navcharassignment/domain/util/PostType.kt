package com.looker.navcharassignment.domain.util

import com.looker.navcharassignment.domain.model.Post

sealed interface PostType {

	object Text : PostType

	data class SingleImage(val image: String) : PostType

	data class MultipleImages(val images: List<String>) : PostType

}

val Post.contentType
	get() = when (images.size) {
		0 -> PostType.Text
		1 -> if (images.first().isNotBlank()) PostType.SingleImage(images.first())
		else PostType.Text
		else -> PostType.MultipleImages(images)
	}
