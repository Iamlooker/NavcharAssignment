package com.looker.navcharassignment.ui.home_page

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.looker.navcharassignment.domain.model.Post

const val homeNavigationRoute = "home_route"

// Currently unused, but can be used to navigate back
fun NavController.navigateToHome(navOptions: NavOptions? = null) {
	this.navigate(homeNavigationRoute, navOptions)
}

fun NavGraphBuilder.homeScreen(
	contentPadding: PaddingValues = PaddingValues(),
	onCommentClick: (Post) -> Unit
) {
	composable(route = homeNavigationRoute) {
		HomePage(
			onCommentClick = onCommentClick,
			contentPadding = contentPadding
		)
	}
}