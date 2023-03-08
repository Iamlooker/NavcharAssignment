package com.looker.navcharassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.looker.navcharassignment.ui.comments_page.CommentsViewModel
import com.looker.navcharassignment.ui.comments_page.commentsPage
import com.looker.navcharassignment.ui.comments_page.navigateToComments
import com.looker.navcharassignment.ui.components.NavcharAppBar
import com.looker.navcharassignment.ui.home_page.homeNavigationRoute
import com.looker.navcharassignment.ui.home_page.homeScreen
import com.looker.navcharassignment.ui.navigation.rememberNavigationState
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

	// This is to provide ViewModel for Comments page easily
	@EntryPoint
	@InstallIn(ActivityComponent::class)
	interface ViewModelFactoryProvider {
		fun commentViewModelFactory(): CommentsViewModel.Factory
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		WindowCompat.setDecorFitsSystemWindows(window, false)
		super.onCreate(savedInstanceState)
		setContent {
			NavcharApp {
				NavcharNavGraph()
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavcharNavGraph(
	navController: NavHostController = rememberNavController()
) {
	val navigationState = rememberNavigationState(navController)
	Scaffold(
		topBar = {
			NavcharAppBar(navigationState = navigationState)
		}
	) {
		NavHost(
			navController = navigationState.navController,
			startDestination = homeNavigationRoute
		) {
			homeScreen(contentPadding = it) { navController.navigateToComments(it.id ?: 1) }
			commentsPage(contentPadding = it)
		}
	}
}