package com.looker.navcharassignment.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.looker.navcharassignment.ui.navigation.NavigationState
import com.looker.navcharassignment.ui.theme.NavcharIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavcharAppBar(
	navigationState: NavigationState,
	modifier: Modifier = Modifier,
	onBackPressed: () -> Unit = { navigationState.navController.popBackStack() }
) {
	TopAppBar(
		modifier = modifier,
		title = {
			Text(text = navigationState.topBarTitle ?: "null")
		},
		navigationIcon = {
			if (navigationState.showHomeUp) {
				IconButton(onClick = onBackPressed) {
					Icon(
						imageVector = NavcharIcons.Back,
						contentDescription = null
					)
				}
			}
		}
	)
}