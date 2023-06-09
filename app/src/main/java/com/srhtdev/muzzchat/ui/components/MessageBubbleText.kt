package com.srhtdev.muzzchat.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.srhtdev.muzzchat.ui.model.ChatUiModel

@Composable
fun ColumnScope.MessageBubbleContent(
    text: ChatUiModel.MessageItem,
    textColor: Color,
    modifier: Modifier = Modifier,
    hasTail: Boolean = false
) {
    Text(
        text = text.message.chatMessage.content,
        color = textColor,
        modifier = modifier
            .align(CenterHorizontally)
            .padding(vertical = 8.dp, horizontal = 16.dp)

    )
    if (hasTail)
        Icon(
            Icons.Filled.Done,
            tint = Color.Green,
            contentDescription = "Message Sent",
            modifier = Modifier
                .align(Alignment.End)
                .padding(bottom = 4.dp, end = 4.dp)
        )
}