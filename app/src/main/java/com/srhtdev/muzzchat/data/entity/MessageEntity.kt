package com.srhtdev.muzzchat.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "user_name")
    val senderName: String = "",
    @ColumnInfo(name = "content")
    val content: String = "",
    @ColumnInfo(name = "message_time")
    val messageTime: Long = 0L
)