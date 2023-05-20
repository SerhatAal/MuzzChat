package com.srhtdev.muzzchat.di

import com.srhtdev.muzzchat.domain.repository.MessageRepository
import com.srhtdev.muzzchat.domain.usecase.GetAllMessagesUseCase
import com.srhtdev.muzzchat.domain.usecase.SendMessageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideAllMessagesUseCases(
        repository: MessageRepository,
    ): GetAllMessagesUseCase {
        return GetAllMessagesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSendMessagesUseCases(
        repository: MessageRepository,
    ): SendMessageUseCase {
        return SendMessageUseCase(repository)
    }
}