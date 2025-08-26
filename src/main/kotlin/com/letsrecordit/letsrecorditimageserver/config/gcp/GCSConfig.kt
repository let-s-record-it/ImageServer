package com.letsrecordit.letsrecorditimageserver.config.gcp

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.storage.Storage
import com.google.cloud.storage.StorageOptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.util.ResourceUtils

@Configuration
@Profile("!test")
class GCSConfig(
    @Value("\${spring.cloud.gcp.storage.credentials.location}")
    private val keyFileName: String,
) {

    @Bean
    fun storage(): Storage {
        val keyFile = ResourceUtils.getURL(keyFileName).openStream()

        return StorageOptions.newBuilder()
            .setCredentials(GoogleCredentials.fromStream(keyFile))
            .build()
            .service
    }
}