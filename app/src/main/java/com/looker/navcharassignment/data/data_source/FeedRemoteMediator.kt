package com.looker.navcharassignment.data.data_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import coil.network.HttpException
import com.looker.navcharassignment.data.data_source.local.FeedDatabase
import com.looker.navcharassignment.data.data_source.local.model.RemoteKeys
import com.looker.navcharassignment.data.data_source.remote.FakeRemoteDataSource
import com.looker.navcharassignment.domain.model.Post
import java.io.IOException
import java.util.concurrent.TimeUnit

private const val FEED_STARTING_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class FeedRemoteMediator(
	private val database: FeedDatabase,
	private val remoteService: FakeRemoteDataSource = FakeRemoteDataSource
) : RemoteMediator<Int, Post>() {

	override suspend fun initialize(): InitializeAction {
		val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)
		val createdSince =
			System.currentTimeMillis() - (database.remoteKeyDao.getCreationTime() ?: 0)

		return if (createdSince < cacheTimeout) {
			InitializeAction.SKIP_INITIAL_REFRESH
		} else {
			InitializeAction.LAUNCH_INITIAL_REFRESH
		}
	}

	override suspend fun load(
		loadType: LoadType,
		state: PagingState<Int, Post>
	): MediatorResult {
		try {
			val page: Int = when (loadType) {
				LoadType.REFRESH -> {
					val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
					remoteKeys?.nextKey?.minus(1) ?: FEED_STARTING_PAGE_INDEX
				}
				LoadType.PREPEND -> {
					val remoteKeys = getRemoteKeyForFirstItem(state)
					val prevKey = remoteKeys?.prevKey
						?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
					prevKey
				}
				LoadType.APPEND -> {
					val remoteKeys = getRemoteKeyForLastItem(state)
					val nextKey = remoteKeys?.nextKey
						?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
					nextKey
				}
			}
			val posts = remoteService.getItems(page)
			val endOfPaginationReached = posts.isEmpty()

			database.withTransaction {
				if (loadType == LoadType.REFRESH) {
					database.remoteKeyDao.clearRemoteKeys()
					database.feedDao.clearAllPosts()
				}
				val prevKey = if (page == FEED_STARTING_PAGE_INDEX) null else page - 1
				val nextKey = if (endOfPaginationReached) null else page + 1
				val keys = posts.map {
					RemoteKeys(
						postId = it.postTime,
						prevKey = prevKey,
						nextKey = nextKey
					)
				}
				database.remoteKeyDao.insertAll(keys)
				database.feedDao.insertPosts(posts)
			}

			return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
		} catch (e: IOException) {
			return MediatorResult.Error(e)
		} catch (e: HttpException) {
			return MediatorResult.Error(e)
		}
	}

	private suspend fun getRemoteKeyForLastItem(
		state: PagingState<Int, Post>
	): RemoteKeys? =
		state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
			?.let { post ->
				database.remoteKeyDao.remoteKeysPostId(post.postTime)
			}

	private suspend fun getRemoteKeyForFirstItem(
		state: PagingState<Int, Post>
	): RemoteKeys? =
		state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
			?.let { post ->
				database.remoteKeyDao.remoteKeysPostId(post.postTime)
			}

	private suspend fun getRemoteKeyClosestToCurrentPosition(
		state: PagingState<Int, Post>
	): RemoteKeys? =
		state.anchorPosition?.let { position ->
			state.closestItemToPosition(position)?.postTime?.let {
				database.remoteKeyDao.remoteKeysPostId(it)
			}
		}
}