package com.letsrecordit.letsrecorditimageserver.feedimage.repository

import com.letsrecordit.letsrecorditimageserver.feedimage.domain.FeedImage
import org.springframework.data.jpa.repository.JpaRepository

interface FeedImageRepository: JpaRepository<FeedImage, Long> {
}