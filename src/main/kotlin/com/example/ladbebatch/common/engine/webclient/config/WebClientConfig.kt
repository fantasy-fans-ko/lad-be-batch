package com.example.ladbebatch.common.engine.webclient.config

import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import reactor.netty.resources.ConnectionProvider
import java.time.Duration


@Configuration
class WebClientConfig {
    @Bean
    fun webClient(httpClient: HttpClient): WebClient {
        return WebClient.builder()
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .codecs { clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024) }
            .build()
    }

    @Bean
    fun httpClient(connectionProvider: ConnectionProvider): HttpClient {
        return HttpClient.create(connectionProvider)
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 300000) // 5분
            .doOnConnected { connection ->
                connection.addHandlerLast(ReadTimeoutHandler(300)) // 5분
                    .addHandlerLast(WriteTimeoutHandler(180)) // 3분
            }
    }

    @Bean
    fun connectionProvider(): ConnectionProvider {
        return ConnectionProvider.builder("http-pool")
            .maxConnections(100) // 커넥션 총 개수
            .pendingAcquireTimeout(Duration.ZERO) // 커넥션 풀에서 커넥션을 얻기 위해 기다리는 최대 시간
            .pendingAcquireMaxCount(-1) // 커넥션 풀에서 가져오는 시도 횟수 (-1 : 제한 없음)
            .maxIdleTime(Duration.ofMillis(300000L)) // 커넥션 풀에서 Idle 상태를 유지하는 시간 (5분)
            .build()
    }
}
