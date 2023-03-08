package com.looker.navcharassignment

import android.app.Application
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.looker.navcharassignment.ui.theme.NavcharAssignmentTheme
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application()

@Composable
fun NavcharApp(content: @Composable () -> Unit) {
	NavcharAssignmentTheme {
		Surface(modifier = Modifier.fillMaxSize()) {
			content()
		}
	}
}