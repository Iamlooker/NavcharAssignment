package com.looker.navcharassignment.ui.home_page

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.looker.navcharassignment.domain.model.Post
import com.looker.navcharassignment.ui.home_page.components.FeedList

@Composable
fun HomePage(
	viewModel: HomeViewModel,
	onCommentClick: (Post) -> Unit,
	modifier: Modifier = Modifier,
	contentPadding: PaddingValues = PaddingValues()
) {
	val listState = rememberLazyListState()
	FeedList(
		modifier = modifier,
		data = viewModel.posts,
		state = listState,
		contentPadding = contentPadding,
		onCommentClick = onCommentClick
	)
}