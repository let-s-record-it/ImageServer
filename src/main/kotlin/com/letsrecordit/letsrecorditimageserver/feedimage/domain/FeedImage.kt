package com.letsrecordit.letsrecorditimageserver.feedimage.domain

import com.letsrecordit.letsrecorditimageserver.global.domain.BaseTime
import jakarta.persistence.Entity
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault

@Entity
class FeedImage(
    @Column(nullable = false)
    var imageUrl: String,

    @Column(nullable = false)
    @ColumnDefault("false")
    var deleted: Boolean,

    @Column(nullable = false)
    var feedId: Long,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_image_id")
    var id: Long? = null,
) : BaseTime() {
}