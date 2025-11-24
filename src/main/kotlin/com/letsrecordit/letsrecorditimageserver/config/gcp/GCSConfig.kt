package com.letsrecordit.letsrecorditimageserver.config.gcp

import com.google.cloud.storage.Storage
import com.google.cloud.storage.StorageOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile("!test")
class GCSConfig() {

    @Bean
    fun storage(): Storage {
        return StorageOptions.getDefaultInstance().service;
    }
}