package com.letsrecordit.letsrecorditimageserver.feedimage.dto

data class FeedImageMessage(
    val feedId: Long,
    val fileName: String,
    val contentType: String,
    val fileBytes: ByteArray,
)
