package com.srhtdev.muzzchat.data.repository

import com.srhtdev.muzzchat.data.MuzzChatDatabase
import com.srhtdev.muzzchat.domain.model.Message
import com.srhtdev.muzzchat.domain.repository.MessageRepository
import com.srhtdev.muzzchat.domain.mapper.toExternal
import com.srhtdev.muzzchat.domain.mapper.toLocal
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject


class MessageRepositoryImpl @Inject constructor(
    private val appDatabase: MuzzChatDatabase
) : MessageRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getAllMessages(): Flow<List<Message>> =
        appDatabase.messageDao().getAllMessages().mapLatest { messages ->
            messages.map {
                it.toExternal()
            }
        }

    override suspend fun sendMessage(message: Message) {
        message.toLocal().let { appDatabase.messageDao().insertMessage(it) }
    }
}