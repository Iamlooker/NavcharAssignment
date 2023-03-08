package com.looker.navcharassignment.data.data_source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys(
	@PrimaryKey
	val postId: Long,
	val prevKey: Int?,
	val nextKey: Int?,
	@ColumnInfo(name = "created_at")
	val createdAt: Long = System.currentTimeMillis()
)