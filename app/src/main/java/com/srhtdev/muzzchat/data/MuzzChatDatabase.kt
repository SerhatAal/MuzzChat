package com.srhtdev.muzzchat.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.srhtdev.muzzchat.data.dao.MessageDao
import com.srhtdev.muzzchat.data.entity.MessageEntity

@Database(entities = [MessageEntity::class], version = 1, exportSchema = false)
abstract class MuzzChatDatabase : RoomDatabase() {

    abstract fun messageDao(): MessageDao
}