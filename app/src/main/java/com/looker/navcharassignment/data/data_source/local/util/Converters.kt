package com.looker.navcharassignment.data.data_source.local.util

import androidx.room.TypeConverter
import com.looker.navcharassignment.domain.model.Category
import com.looker.navcharassignment.domain.model.Comment
import com.looker.navcharassignment.domain.model.User
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private val json = Json {
	ignoreUnknownKeys = true
	encodeDefaults = true
}

private val commentsListSerializer = ListSerializer(Comment.serializer())

class CommentsConverters {

	@TypeConverter
	fun commentToString(comment: Comment): String = json.encodeToString(comment)

	@TypeConverter
	fun stringToComment(string: String): Comment = json.decodeFromString(string)

	@TypeConverter
	fun commentsListToString(comment: List<Comment>): String =
		json.encodeToString(commentsListSerializer, comment)

	@TypeConverter
	fun stringToCommentsList(string: String): List<Comment> =
		json.decodeFromString(commentsListSerializer, string)

}

class CategoryConverters {

	@TypeConverter
	fun categoryToString(category: Category): String = json.encodeToString(category)

	@TypeConverter
	fun stringToCategory(string: String): Category = json.decodeFromString(string)

}

class ImagesConverters {

	private val delimiter = "!@#$%^&*!@#$%^"

	@TypeConverter
	fun imagesToString(images: List<String>): String = images.joinToString(delimiter)

	@TypeConverter
	fun stringToImages(string: String): List<String> = string.split(delimiter)

}

class UserConverters {

	@TypeConverter
	fun userToString(user: User): String = json.encodeToString(user)

	@TypeConverter
	fun stringToUser(string: String): User = json.decodeFromString(string)

}