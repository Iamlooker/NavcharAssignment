package com.looker.navcharassignment.ui.home_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.RemoteMediator
import androidx.paging.cachedIn
import com.looker.navcharassignment.data.data_source.remote.FakeRemoteDataSource
import com.looker.navcharassignment.data.repository.OfflineFirstFeedRepository
import com.looker.navcharassignment.domain.model.Post
import com.looker.navcharassignment.domain.repository.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val repo: FeedRepository
) : ViewModel() {

	val posts = repo.getPostsPaginated().cachedIn(viewModelScope)

	fun syncWithRemote() {
		viewModelScope.launch {
			repo.syncRemote()
		}
	}
}

