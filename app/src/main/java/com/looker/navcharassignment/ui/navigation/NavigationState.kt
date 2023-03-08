package com.looker.navcharassignment.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.looker.navcharassignment.ui.comments_page.commentsNavigationRoute
import com.looker.navcharassignment.ui.home_page.homeNavigationRoute
import com.looker.navcharassignment.ui.home_page.navigateToHome

@Composable
fun rememberNavigationState(
	navController: NavHostController = rememberNavController()
): NavigationState = remember(navController) {
	NavigationState(navController)
}

@Stable
class NavigationState(
	val navController: NavHostController
) {

	private val currentDestination: NavDestination?
		@Composable get() = navController
			.currentBackStackEntryAsState().value?.destination

	val topBarTitle: String?
		@Composable get() = if (currentDestination?.route?.contains(commentsNavigationRoute) == true) {
			"Comments"
		} else if (currentDestination?.route == homeNavigationRoute) {
			"Navchar App"
		} else null

	val showHomeUp: Boolean
		@Composable get() = when (currentDestination?.route) {
			homeNavigationRoute -> false
			else -> true
		}

}