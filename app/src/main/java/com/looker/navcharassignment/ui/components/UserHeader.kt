package com.looker.navcharassignment.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.looker.navcharassignment.domain.model.User
import com.looker.navcharassignment.ui.home_page.components.NetworkImage
import com.looker.navcharassignment.ui.theme.NavcharAssignmentTheme

@Composable
fun UserHeader(
	user: User,
	modifier: Modifier = Modifier,
	subtitle: (@Composable () -> Unit)? = null,
	label: (@Composable RowScope.() -> Unit)? = null
) {
	Row(
		modifier = modifier,
		verticalAlignment = Alignment.CenterVertically
	) {
		NetworkImage(
			data = user.profilePic,
			modifier = Modifier
				.size(40.dp)
				.clip(CircleShape)
		)
		Spacer(modifier = Modifier.width(12.dp))
		Column(
			verticalArrangement = Arrangement.SpaceAround
		) {
			Text(
				text = user.name,
				style = MaterialTheme.typography.bodyLarge
			)
			CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.outline) {
				if (subtitle != null) subtitle()
			}
		}
		if (label != null){
			Spacer(modifier = Modifier.width(12.dp))
			label()
		}
	}
}

@Preview
@Composable
fun UserHeaderPrev() {
	NavcharAssignmentTheme {
		UserHeader(user = User.DEFAULT)
	}
}