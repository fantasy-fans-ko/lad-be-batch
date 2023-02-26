package com.example.ladbebatch.team.chunk.reader

import com.example.ladbebatch.common.logger
import com.example.ladbebatch.model.Team
import org.springframework.batch.item.database.JpaCursorItemReader
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.persistence.EntityManagerFactory

@Component
class TeamReader(
    @Autowired val entityManagerFactory: EntityManagerFactory,
) {
    fun getTeamAll() : JpaCursorItemReader<Team> {
        logger().info(">>>>>>>>>>>>>>>> Reader")

        return JpaCursorItemReaderBuilder<Team>()
            .name("getTeam")
            .entityManagerFactory(entityManagerFactory)
            .queryString("SELECT t FROM teams t")
            .build();
    }
}
