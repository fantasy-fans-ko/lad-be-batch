package com.example.ladbebatch.common

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.client.RestTemplate

abstract class RestTemplateClient {
    @Autowired
    lateinit var restTemplate: RestTemplate

    inline fun <reified R> getData(url: String): R {
        logger().info(">>>>>>>>>>>>>>>> RestTemplate API : $url <<<<<<<<<<<<<<<<<<<<<<")
        return restTemplate.getForObject(url, R::class.java) ?: throw RuntimeException("error")
    }
}
