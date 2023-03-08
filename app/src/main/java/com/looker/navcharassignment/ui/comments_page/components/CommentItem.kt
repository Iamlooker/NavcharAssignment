package com.looker.navcharassignment.ui.comments_page.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.looker.navcharassignment.domain.model.Comment
import com.looker.navcharassignment.ui.components.InteractionItem
import com.looker.navcharassignment.ui.components.InteractionSources
import com.looker.navcharassignment.ui.components.UserHeader
import com.looker.navcharassignment.ui.theme.NavcharIcons

@Composable
fun CommentItem(
	comment: Comment,
	modifier: Modifier = Modifier
) {
	Column(modifier = modifier) {
		UserHeader(
			user = comment.user,
			subtitle = {
				Text(
					text = "Public",
					style = MaterialTheme.typography.bodySmall
				)
			}
		)
		Spacer(modifier = Modifier.height(8.dp))
		Text(text = comment.text)
		InteractionSources {
			InteractionItem(icon = NavcharIcons.Like, text = "Like") {
				// On Click
			}
		}
	}
}