package com.looker.navcharassignment.ui.comments_page

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.looker.navcharassignment.MainActivity
import com.looker.navcharassignment.data.repository.OfflineFirstFeedRepository
import com.looker.navcharassignment.domain.model.Post
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class CommentsViewModel @AssistedInject constructor(
	repo: OfflineFirstFeedRepository,
	@Assisted private val postId: Long
) : ViewModel() {

	val currentPost = repo.getPost(postId)
		.stateIn(
			scope = viewModelScope,
			started = SharingStarted.WhileSubscribed(5_000),
			initialValue = Post.DEFAULT
		)

	val comments = repo.getComments(postId)
		.stateIn(
			scope = viewModelScope,
			started = SharingStarted.WhileSubscribed(5_000),
			initialValue = emptyList()
		)

	@AssistedFactory
	interface Factory {
		fun create(postId: Long): CommentsViewModel
	}

	companion object {
		fun providesFactory(
			assistedFactory: Factory,
			postId: Long
		): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
			override fun <T : ViewModel> create(modelClass: Class<T>): T {

				// using our ArticlesFeedViewModelFactory's create function
				// to actually create our viewmodel instance
				return assistedFactory.create(postId) as T
			}
		}
	}
}


@Composable
fun commentViewModel(postId: Long): CommentsViewModel {

	val factory = EntryPointAccessors.fromActivity(
		LocalContext.current as Activity,
		MainActivity.ViewModelFactoryProvider::class.java
	).commentViewModelFactory()

	return viewModel(factory = CommentsViewModel.providesFactory(factory, postId))
}