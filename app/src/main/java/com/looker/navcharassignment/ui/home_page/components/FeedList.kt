package com.looker.navcharassignment.ui.home_page.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.looker.navcharassignment.domain.model.Post
import kotlinx.coroutines.flow.Flow

@Composable
fun FeedList(
	data: Flow<PagingData<Post>>,
	onCommentClick: (Post) -> Unit,
	modifier: Modifier = Modifier,
	state: LazyListState = rememberLazyListState(),
	contentPadding: PaddingValues = PaddingValues()
) {
	val posts = data.collectAsLazyPagingItems()
	LazyColumn(
		modifier = modifier,
		state = state,
		verticalArrangement = Arrangement.spacedBy(8.dp),
		contentPadding = contentPadding
	) {
		items(items = posts) { post ->
			post?.let {
				FeedItem(
					modifier = Modifier.fillMaxWidth(),
					post = it,
					onCommentClick = { onCommentClick(post) }
				)
			}
		}

		posts.apply {
			when {
				loadState.refresh is LoadState.Loading -> {
					item {
						CircularProgressIndicator(
							modifier = Modifier
								.fillParentMaxSize()
								.wrapContentSize(Alignment.Center)
						)
					}
				}
				loadState.append is LoadState.Loading -> {
					item {
						CircularProgressIndicator(
							modifier = Modifier
								.fillMaxWidth()
								.wrapContentWidth()
						)
					}
				}
				loadState.refresh is LoadState.Error -> {
					val e = loadState.refresh as LoadState.Error
					item {
						TextButton(onClick = { retry() }) {
							Text(text = e.error.message.toString())
						}
					}
				}
				loadState.append is LoadState.Error -> {
					val e = loadState.append as LoadState.Error
					item {
						TextButton(onClick = { retry() }) {
							Text(text = e.error.message.toString())
						}
					}
				}
			}
		}
	}
}