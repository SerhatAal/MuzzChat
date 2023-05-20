package com.srhtdev.muzzchat.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.srhtdev.muzzchat.ui.model.ChatUiModel

@Composable
fun ColumnScope.MessageBubbleContent(
    text: ChatUiModel.MessageItem,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Text(
        text = text.message.chatMessage.content,
        color = textColor,
        modifier = modifier
            .align(Alignment.CenterHorizontally)
            .then(
                Modifier.padding(vertical = 12.dp, horizontal = 16.dp)
            )
    )
}