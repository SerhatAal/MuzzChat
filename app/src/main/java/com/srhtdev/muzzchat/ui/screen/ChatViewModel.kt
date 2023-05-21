package com.srhtdev.muzzchat.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srhtdev.muzzchat.domain.model.Message
import com.srhtdev.muzzchat.domain.usecase.GetAllMessagesUseCase
import com.srhtdev.muzzchat.domain.usecase.SendMessageUseCase
import com.srhtdev.muzzchat.ui.model.ChatUiModel
import com.srhtdev.muzzchat.ui.model.MessageUiModel
import com.srhtdev.muzzchat.ui.model.User
import com.srhtdev.muzzchat.util.Constants.ONE_HOUR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getAllMessagesUseCase: GetAllMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
) : ViewModel() {
    private val _allMessages: MutableStateFlow<List<ChatUiModel>> =
        MutableStateFlow(emptyList())
    val allMessages: Flow<List<ChatUiModel>> = _allMessages

    private val _currentUser: MutableStateFlow<User> =
        MutableStateFlow(User.UserOne)
    val currentUser: Flow<User> = _currentUser

    init {
        getAllMessages()
    }

    private fun getAllMessages() {
        viewModelScope.launch {
            try {
                getAllMessagesUseCase.invoke().collect { list ->
                    val messages = list.map {
                        if (it.senderName == _currentUser.value.name) {
                            MessageUiModel(it, true)
                        } else {
                            MessageUiModel(it, false)
                        }
                    }

                    val uiList = sectionMessages(messages)
                    _allMessages.value = uiList
                }

            } catch (e: Throwable) {
                println("error$e")
            }
        }
    }

    fun sendMessage(content: String) {

        val message = Message(
            senderName = _currentUser.value.name,
            content = content,
            messageTime = System.currentTimeMillis()
        )

        viewModelScope.launch {
            try {
                sendMessageUseCase(message)
                getAllMessages()
            } catch (e: Throwable) {
                println("error$e")
            }
        }
    }

    fun switchUser() {
        val user = _currentUser.value.name

        if (user == User.UserOne.name)
            _currentUser.value = User.UserTwo
        else {
            _currentUser.value = User.UserOne
        }

        getAllMessages()
    }

    private fun sectionMessages(messages: List<MessageUiModel>): List<ChatUiModel> {
        val sectionedMessages = mutableListOf<ChatUiModel>()

        var previousMessageTime: Date? = null

        for (message in messages) {
            val currentMessageTime = Date(message.chatMessage.messageTime)

            if (previousMessageTime == null || currentMessageTime.time - previousMessageTime.time > ONE_HOUR) {
                sectionedMessages.add(ChatUiModel.DateItem(message.chatMessage.messageTime))
            }

            sectionedMessages.add(
                ChatUiModel.MessageItem(message)
            )

            previousMessageTime = currentMessageTime
        }

        return sectionedMessages
    }
}