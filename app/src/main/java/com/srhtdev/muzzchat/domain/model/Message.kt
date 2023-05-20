package com.srhtdev.muzzchat.domain.model

data class Message(
    val id: Int? = null,
    val senderName: String,
    val content: String,
    val messageTime: Long
)
