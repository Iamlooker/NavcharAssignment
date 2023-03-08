package com.looker.navcharassignment.ui.comments_page

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val commentsNavigationRoute = "comments_route"

private const val postIdArg = "postId"

fun NavController.navigateToComments(postId: Long, navOptions: NavOptions? = null) {
	this.navigate("$commentsNavigationRoute/$postId", navOptions)
}

fun NavGraphBuilder.commentsPage(contentPadding: PaddingValues = PaddingValues()) {
	composable(
		route = "$commentsNavigationRoute/{$postIdArg}",
		arguments = listOf(
			navArgument(postIdArg) { type = NavType.LongType }
		)
	) {
		CommentsPage(postId = it.arguments?.getLong(postIdArg), contentPadding = contentPadding)
	}
}
