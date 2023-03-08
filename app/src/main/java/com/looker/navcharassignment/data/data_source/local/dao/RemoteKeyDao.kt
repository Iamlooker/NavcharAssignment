package com.looker.navcharassignment.data.data_source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.looker.navcharassignment.data.data_source.local.model.RemoteKeys

@Dao
interface RemoteKeysDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertAll(remoteKey: List<RemoteKeys>)

	@Query("SELECT * FROM remote_keys WHERE postId = :postId")
	suspend fun remoteKeysPostId(postId: Long): RemoteKeys?

	@Query("DELETE FROM remote_keys")
	suspend fun clearRemoteKeys()

	@Query("SELECT created_at FROM remote_keys ORDER BY created_at DESC LIMIT 1")
	suspend fun getCreationTime(): Long?

}