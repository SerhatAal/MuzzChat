package com.srhtdev.muzzchat.ui.model

import com.srhtdev.muzzchat.domain.model.Message

sealed class User(val name: String, val image: String) {
    object UserOne : User("Aliyah", "https://images.unsplash.com/photo-1586682643135-060f061868b6?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=671&q=80")
    object UserTwo : User("Adnan", "https://images.unsplash.com/photo-1547425260-76bcadfb4f2c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1470&q=80")
}

sealed class ChatUiModel {
    data class DateItem(
        val date: Long
    ) : ChatUiModel()

    data class MessageItem(
        val message: MessageUiModel
    ) : ChatUiModel()
}

data class MessageUiModel(
    val chatMessage: Message,
    val isMessageFromOtherUser: Boolean
)