package com.srhtdev.muzzchat.domain.usecase

import com.srhtdev.muzzchat.domain.model.Message
import com.srhtdev.muzzchat.domain.repository.MessageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val repository: MessageRepository,
) {
    suspend operator fun invoke(message: Message) {
        return withContext(Dispatchers.IO) {
            repository.sendMessage(message)
        }
    }
}
