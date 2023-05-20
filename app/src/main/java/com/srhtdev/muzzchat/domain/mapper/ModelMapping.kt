package com.srhtdev.muzzchat.domain.mapper

import com.srhtdev.muzzchat.data.entity.MessageEntity
import com.srhtdev.muzzchat.domain.model.Message


fun Message.toLocal() = MessageEntity(
    senderName = senderName,
    content = content,
    messageTime = messageTime,
)

fun MessageEntity.toExternal() = Message(
    id = id,
    senderName = senderName,
    content = content,
    messageTime = messageTime
)
