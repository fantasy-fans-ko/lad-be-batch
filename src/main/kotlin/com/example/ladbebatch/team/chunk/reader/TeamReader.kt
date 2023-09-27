package com.example.ladbebatch.team.chunk.reader

import com.example.ladbebatch.common.logger
import com.example.ladbebatch.team.model.Team
import jakarta.persistence.EntityManagerFactory
import org.springframework.batch.item.database.JpaCursorItemReader
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class TeamReader(
    @Autowired val entityManagerFactory: EntityManagerFactory,
) {
    fun getAllTeams(): JpaCursorItemReader<Team> {
        logger().info("reader 입니다.")
        return JpaCursorItemReaderBuilder<Team>()
            .name("getTeams")
            .entityManagerFactory(entityManagerFactory)
            .queryString("SELECT t FROM Team t")
            .build()
    }
}
