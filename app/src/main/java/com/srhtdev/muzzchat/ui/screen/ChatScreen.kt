package com.srhtdev.muzzchat.ui.screen

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.srhtdev.muzzchat.ui.components.FriendMessageBubble
import com.srhtdev.muzzchat.ui.components.MessageDateText
import com.srhtdev.muzzchat.ui.components.UserMessageBubble
import com.srhtdev.muzzchat.ui.model.ChatUiModel
import com.srhtdev.muzzchat.ui.model.User
import com.srhtdev.muzzchat.ui.theme.Rose
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen() {

    val viewModel: ChatViewModel = viewModel()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val (toolbar, recyclerView, bottomBar, editText, button) = createRefs()

        Box(
            modifier = Modifier
                .constrainAs(toolbar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            ChatAppToolBar(viewModel)
        }

        val allMessage = viewModel.allMessages.collectAsState(initial = listOf()).value

        val lazyListState = rememberLazyListState()
        LaunchedEffect(allMessage.size) {
            if (allMessage.isNotEmpty())
                lazyListState.scrollToItem(allMessage.size - 1)
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(recyclerView) {
                    top.linkTo(toolbar.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(bottomBar.top)
                }
                .padding(bottom = 80.dp, top = 65.dp),
            state = lazyListState,
            verticalArrangement = Arrangement.Bottom
        ) {

            items(allMessage) { item ->
                when (item) {
                    is ChatUiModel.MessageItem -> {
                        if (item.message.isMessageFromOtherUser) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Spacer(modifier = Modifier.weight(1f))
                                UserMessageBubble(text = item)
                            }
                        } else {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                FriendMessageBubble(text = item)
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }

                    }

                    is ChatUiModel.DateItem -> {
                        val sdfDay = SimpleDateFormat("EEEE", Locale.getDefault())
                        val sdfHour = SimpleDateFormat("HH:mm", Locale.getDefault())

                        MessageDateText(
                            dayString = sdfDay.format(Date(item.date)),
                            hourString = sdfHour.format(Date(item.date))
                        )
                    }
                }
            }
        }

        ConstraintLayout(
            modifier = Modifier
                .constrainAs(bottomBar) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .background(color = Color.White)
        ) {

            var textFieldValue by remember { mutableStateOf("") }


            var isFocused by remember { mutableStateOf(false) }

            OutlinedTextField(
                value = textFieldValue,
                onValueChange = { newValue ->
                    textFieldValue = newValue
                },
                modifier = Modifier
                    .constrainAs(editText) {
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(button.start, 16.dp)
                        bottom.linkTo(parent.bottom, 16.dp)
                    }
                    .background(Color.White)
                    .clip(RoundedCornerShape(30.dp))
                    .border(
                        width = 2.dp,
                        color = if (isFocused) Rose else Color.LightGray,
                        shape = RoundedCornerShape(30.dp)
                    )
                    .focusable(true)
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences
                ),
            )

            IconButton(
                onClick = {
                    if (textFieldValue.isNotEmpty()) {
                        viewModel.sendMessage(textFieldValue)
                        textFieldValue = ""
                    }
                },
                modifier = Modifier
                    .constrainAs(button) {
                        end.linkTo(parent.end, 16.dp)
                    }
                    .clip(CircleShape)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFFfe257c),
                                Color(0xFFfd6470)
                            ),
                            start = Offset.Zero,
                            end = Offset.Infinite
                        )
                    )
            ) {
                Icon(
                    imageVector = Icons.Outlined.Send,
                    contentDescription = "Send Message",
                    modifier = Modifier.height(36.dp),
                    tint = Color.White
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatAppToolBar(
    viewmodel: ChatViewModel,
    modifier: Modifier = Modifier
) {
    val activity = (LocalContext.current as? Activity)

    val currentUser = viewmodel.currentUser.collectAsState(initial = User.UserOne).value

    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { activity?.finish() }) {
                    Icon(
                        Icons.Outlined.KeyboardArrowLeft,
                        tint = Color(0xFFfb4375),
                        contentDescription = "Navigate up",
                        modifier = Modifier.fillMaxSize()
                    )
                }

                AsyncImage(
                    model = currentUser.image,
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(48.dp)
                        .padding(8.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                )

                Text(
                    currentUser.name,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF485866)
                    ),
                    modifier = Modifier
                )
            }
        },
        actions = {
            IconButton(onClick = { viewmodel.switchUser() }) {
                Icon(
                    Icons.Filled.Refresh,
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = "Switch user",
                )
            }
        },
        modifier = modifier
            .height(56.dp)
            .shadow(elevation = 8.dp, shape = RectangleShape),
    )
}
