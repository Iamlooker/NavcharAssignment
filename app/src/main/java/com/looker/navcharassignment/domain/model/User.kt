package com.looker.navcharassignment.domain.model

@kotlinx.serialization.Serializable
data class User(
	val name: String,
	val profilePic: String
) {
	companion object {
		val DEFAULT = User(
			name = "Mohit Sharma",
			profilePic = "https://images.unsplash.com/photo-1677901766272-8c9d7b49f07c?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDQ0fHRvd0paRnNrcEdnfHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=500&q=60"
		)
	}
}
