package com.looker.navcharassignment.domain.model

@kotlinx.serialization.Serializable
sealed class Category(val name: String) {

	@kotlinx.serialization.Serializable
	object Question : Category("Question")

	@kotlinx.serialization.Serializable
	object Marketing : Category("Marketing")

	@kotlinx.serialization.Serializable
	object SoilManagement : Category("Soil Management")

}
