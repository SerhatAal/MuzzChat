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
import com.srhtdev.muzzchat.ui.theme.Rose

@Composable
fun CurrentUserMessageBubble(
    text: ChatUiModel.MessageItem,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 2.dp)
            .background(
                color = Rose,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        MessageBubbleContent(text, Color.White, modifier)
    }
}
