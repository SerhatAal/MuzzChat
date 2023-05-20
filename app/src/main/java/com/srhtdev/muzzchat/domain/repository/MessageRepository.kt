package com.srhtdev.muzzchat.domain.repository

import com.srhtdev.muzzchat.domain.model.Message
import kotlinx.coroutines.flow.Flow

interface MessageRepository {

    fun getAllMessages(): Flow<List<Message>>

    suspend fun sendMessage(message: Message)
}