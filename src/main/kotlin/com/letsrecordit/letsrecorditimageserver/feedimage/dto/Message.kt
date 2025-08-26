package com.letsrecordit.letsrecorditimageserver.feedimage.dto

data class Message<T>(
    val title: String,
    val content: T,
    val type: MessageType,
)
