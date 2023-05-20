package com.srhtdev.muzzchat.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srhtdev.muzzchat.domain.model.Message
import com.srhtdev.muzzchat.domain.usecase.GetAllMessagesUseCase
import com.srhtdev.muzzchat.domain.usecase.SendMessageUseCase
import com.srhtdev.muzzchat.ui.model.MessageUiModel
import com.srhtdev.muzzchat.ui.model.ChatUiModel
import com.srhtdev.muzzchat.ui.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getAllMessagesUseCase: GetAllMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
) : ViewModel() {
    private val _allMessages: MutableStateFlow<List<ChatUiModel>> =
        MutableStateFlow(emptyList())
    val allMessages: Flow<List<ChatUiModel>> = _allMessages

    private val _lastMessage: MutableStateFlow<Message?> =
        MutableStateFlow(null)
    val lastMessage: Flow<Message?> = _lastMessage

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
                    _lastMessage.value = list.last()
                    var messages: List<MessageUiModel> = listOf()
                    list.map {
                        messages = if (it.senderName == _currentUser.value.name) {
                            messages + MessageUiModel(it, true)
                        } else {
                            messages + MessageUiModel(it, false)
                        }
                    }

                    val groupedList = messages.groupBy { message ->
                        val calendar = Calendar.getInstance().apply {
                            timeInMillis = message.chatMessage.messageTime
                            set(Calendar.MINUTE, 0)
                            set(Calendar.SECOND, 0)
                            set(Calendar.MILLISECOND, 0)
                        }
                        calendar.timeInMillis
                    }

                    val uiList = mutableListOf<ChatUiModel>()

                    groupedList.forEach { message ->
                        uiList.add(ChatUiModel.DateItem(message.key))
                        uiList.addAll(message.value.map {
                            ChatUiModel.MessageItem(it)
                        })
                    }
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
}