package com.example.ladbebatch.config

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableAutoConfiguration
@EnableBatchProcessing
@EnableJpaRepositories("com.example.ladbebatch.team.repository")
@EntityScan("com.example.ladbebatch.common.model")
class TestConfig {
}
