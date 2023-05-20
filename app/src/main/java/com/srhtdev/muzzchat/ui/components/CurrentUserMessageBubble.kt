package com.srhtdev.muzzchat.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.srhtdev.muzzchat.ui.model.ChatUiModel
import com.srhtdev.muzzchat.ui.theme.WhiteLilac

@Composable
fun FriendMessageBubble(
    text: ChatUiModel.MessageItem,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 2.dp)
            .background(
                color = WhiteLilac,
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                    bottomEnd = 16.dp,
                    bottomStart = 0.dp
                )
            )
    ) {
        MessageBubbleContent(text, Color.Black, modifier)
    }
}