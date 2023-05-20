package com.srhtdev.muzzchat.di

import com.srhtdev.muzzchat.data.MuzzChatDatabase
import com.srhtdev.muzzchat.data.repository.MessageRepositoryImpl
import com.srhtdev.muzzchat.domain.repository.MessageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideChatRepository(
        appDatabase: MuzzChatDatabase
    ): MessageRepository {
        return MessageRepositoryImpl(
            appDatabase = appDatabase
        )
    }
}