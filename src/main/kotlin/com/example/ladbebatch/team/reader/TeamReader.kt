package com.example.ladbebatch.team.reader

import com.example.ladbebatch.model.Team
import org.springframework.batch.item.database.JpaCursorItemReader
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.persistence.EntityManagerFactory

@Component
class TeamReader(
    @Autowired val entityManagerFactory: EntityManagerFactory
) {
    fun getCursorJpaReader(): JpaCursorItemReader<Team> =
        JpaCursorItemReaderBuilder<Team>()
            .entityManagerFactory(entityManagerFactory)
            .queryString("SELECT t FROM Team t")
            .build()
}
