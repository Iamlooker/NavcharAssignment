package com.looker.navcharassignment.ui.comments_page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.looker.navcharassignment.domain.model.Comment
import com.looker.navcharassignment.domain.model.Post
import com.looker.navcharassignment.ui.comments_page.components.CommentItem
import com.looker.navcharassignment.ui.home_page.components.FeedItem

@Composable
fun CommentsPage(
	postId: Long?,
	modifier: Modifier = Modifier,
	viewModel: CommentsViewModel = commentViewModel(postId = postId ?: 1),
	contentPadding: PaddingValues = PaddingValues()
) {
	val currentPost by viewModel.currentPost.collectAsState()
	val comments by viewModel.comments.collectAsState()
	FeedWithComment(
		modifier = modifier,
		post = currentPost,
		comments = comments,
		contentPadding = contentPadding
	)
}

@Composable
fun FeedWithComment(
	post: Post,
	comments: List<Comment>,
	modifier: Modifier = Modifier,
	contentPadding: PaddingValues = PaddingValues()
) {
	LazyColumn(
		modifier = modifier,
		verticalArrangement = Arrangement.spacedBy(8.dp),
		contentPadding = contentPadding
	) {
		item {
			FeedItem(post = post)
		}
		item {
			Divider()
		}
		items(comments) {
			CommentItem(
				modifier = Modifier.padding(horizontal = 16.dp), comment = it
			)
		}
	}
}