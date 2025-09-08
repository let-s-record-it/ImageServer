package com.letsrecordit.letsrecorditimageserver.feedimage.service

import com.letsrecordit.letsrecorditimageserver.feedimage.dto.FeedImageMessage
import com.letsrecordit.letsrecorditimageserver.feedimage.dto.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
class MessageListener(
    private val feedImageUploader: FeedImageUploader,
) {

    @RabbitListener(queues = ["\${rabbitmq.queue.name}"])
    fun listen(message: Message<List<FeedImageMessage>>) {
        feedImageUploader.uploadImages(message.content)
    }
}