package com.looker.navcharassignment.ui.home_page.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.looker.navcharassignment.domain.model.Post
import com.looker.navcharassignment.domain.util.PostType
import com.looker.navcharassignment.domain.util.contentType
import com.looker.navcharassignment.ui.components.InteractionItem
import com.looker.navcharassignment.ui.components.InteractionSources
import com.looker.navcharassignment.ui.components.Label
import com.looker.navcharassignment.ui.components.UserHeader
import com.looker.navcharassignment.ui.theme.NavcharIcons
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@Composable
fun FeedItem(
	post: Post,
	modifier: Modifier = Modifier,
	onLikeClick: () -> Unit = {},
	onCommentClick: () -> Unit = {},
	onShareClick: () -> Unit = {}
) {
	val postType by remember(post) { mutableStateOf(post.contentType) }
	Column(
		modifier = modifier,
		verticalArrangement = Arrangement.spacedBy(12.dp)
	) {
		UserHeader(
			modifier = Modifier.padding(horizontal = 16.dp),
			user = post.user,
			subtitle = {
				val currentDuration by remember(post) {
					mutableStateOf(
						(System.currentTimeMillis() - post.postTime)
							.toDuration(DurationUnit.MILLISECONDS)
							.toString(DurationUnit.MINUTES)
					)
				}
				Text(
					text = currentDuration,
					style = MaterialTheme.typography.bodySmall
				)
			},
			label = {
				Label(text = post.category.name)
			}
		)
		Text(
			modifier = Modifier.padding(horizontal = 16.dp),
			text = post.title
		)
		FeedItemMultimedia(postType)
		InteractionSources(Modifier.fillMaxWidth()) {
			InteractionItem(
				icon = NavcharIcons.Like,
				text = "${post.likes} likes",
				onClick = onLikeClick
			)
			InteractionItem(
				icon = NavcharIcons.Comments,
				text = "${post.comments.size} comments",
				onClick = onCommentClick
			)
			InteractionItem(
				icon = NavcharIcons.Share,
				text = "Share",
				onClick = onShareClick
			)
		}
	}
}

@Composable
fun FeedItemMultimedia(
	postType: PostType,
	modifier: Modifier = Modifier
) {
	when (postType) {
		is PostType.MultipleImages -> HorizontalImageList(
			modifier = modifier,
			data = postType.images
		)
		is PostType.SingleImage -> {
			NetworkImage(
				data = postType.image,
				modifier = modifier
					.fillMaxWidth()
					.height(280.dp)
			)
		}
		PostType.Text -> Spacer(modifier = Modifier.height(Dp.Hairline))
	}
}

