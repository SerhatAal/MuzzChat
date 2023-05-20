package com.srhtdev.muzzchat.di

import android.content.Context
import androidx.room.Room
import com.srhtdev.muzzchat.data.MuzzChatDatabase
import com.srhtdev.muzzchat.data.dao.MessageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MuzzChatDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MuzzChatDatabase::class.java,
            "Chat.db"
        ).build()
    }

    @Provides
    fun provideMessageDao(database: MuzzChatDatabase): MessageDao = database.messageDao()
}