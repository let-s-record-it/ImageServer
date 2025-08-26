package com.letsrecordit.letsrecorditimageserver.feedimage.service

import com.google.cloud.storage.BlobInfo
import com.google.cloud.storage.Storage
import com.letsrecordit.letsrecorditimageserver.feedimage.domain.FeedImage
import com.letsrecordit.letsrecorditimageserver.feedimage.dto.FeedImageMessage
import com.letsrecordit.letsrecorditimageserver.feedimage.repository.FeedImageRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.io.ByteArrayInputStream

private const val GCS_HOST = "https://storage.googleapis.com/"

@Service
class FeedImageUploader(
    @Value("\${spring.cloud.gcp.storage.bucket}")
    private val bucketName: String,
    private val storage: Storage,
    private val feedImageRepository: FeedImageRepository,
) {

    @Transactional
    fun uploadImages(images: List<FeedImageMessage>) {
        for (image in images) {
            uploadImage(image)
        }
    }

    fun uploadImage(image: FeedImageMessage) {
        storage.createFrom(
            BlobInfo.newBuilder(bucketName, image.fileName).setContentType(image.contentType).build(),
            ByteArrayInputStream(image.fileBytes)
        )
        val imageUrl = GCS_HOST + bucketName + "/" + image.fileName
        feedImageRepository.save(FeedImage(imageUrl, false, image.feedId))
    }
}