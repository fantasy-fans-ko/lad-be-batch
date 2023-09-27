package com.example.ladbebatch.common.engine.webclient.client

import com.example.ladbebatch.common.engine.Exception.BadWebClientRequestException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

abstract class WebClientTemplate {
    @Autowired
    lateinit var webClient: WebClient

    inline fun <reified R> get(
        url: String
    ): Mono<ResponseEntity<R>> {
        return webClient.method(HttpMethod.GET)
            .uri(url)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve() // 데이터를 받을 때, 2가지의 형태로 받을 수 있다.
            .onStatus(HttpStatus.Series.CLIENT_ERROR::equals) {
                Mono.error<BadWebClientRequestException>(
                    BadWebClientRequestException(it.statusCode().value(), it.bodyToMono(String::class.java).toString())
                )
            }
            .onStatus(HttpStatus.Series.SERVER_ERROR::equals) {
                Mono.error<BadWebClientRequestException>(
                    BadWebClientRequestException(
                        it.statusCode().value(), it.bodyToMono(
                            String::class.java
                        ).toString()
                    )
                )
            }
            .toEntity(R::class.java)
    }

    inline fun <reified R> post(
        url: String,
        body: Any
    ): Mono<ResponseEntity<R>> {
        return webClient.method(HttpMethod.POST)
            .uri(url)
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(body)
            .retrieve()
            .onStatus(HttpStatus.Series.CLIENT_ERROR::equals) {
                Mono.error(
                    BadWebClientRequestException(
                        it.statusCode().value(), it.bodyToMono(
                            String::class.java
                        ).toString()
                    )
                )
            }
            .onStatus(HttpStatus.Series.SERVER_ERROR::equals) {
                Mono.error(
                    BadWebClientRequestException(
                        it.statusCode().value(), it.bodyToMono(
                            String::class.java
                        ).toString()
                    )
                )
            }
            .toEntity(R::class.java)
    }

    inline fun <reified R> patch(
        url: String,
        body: Any
    ): Mono<ResponseEntity<R>> {
        return webClient.method(HttpMethod.PATCH)
            .uri(url)
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(body)
            .retrieve()
            .onStatus(HttpStatus.Series.CLIENT_ERROR::equals) {
                Mono.error(
                    BadWebClientRequestException(
                        it.statusCode().value(), it.bodyToMono(
                            String::class.java
                        ).toString()
                    )
                )
            }
            .onStatus(HttpStatus.Series.SERVER_ERROR::equals) {
                Mono.error(
                    BadWebClientRequestException(
                        it.statusCode().value(), it.bodyToMono(
                            String::class.java
                        ).toString()
                    )
                )
            }
            .toEntity(R::class.java)
    }
}
