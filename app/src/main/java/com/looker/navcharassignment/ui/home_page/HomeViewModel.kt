package com.looker.navcharassignment.ui.home_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.looker.navcharassignment.domain.repository.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val repo: FeedRepository
) : ViewModel() {

	val posts = repo.getPostsPaginated().cachedIn(viewModelScope)

	// If planning to add `Pull to Refresh`
	fun syncWithRemote() {
		viewModelScope.launch {
			repo.syncRemote()
		}
	}
}

