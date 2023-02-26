package com.example.ladbebatch.team.chunk.writer

import com.example.ladbebatch.common.logger
import com.example.ladbebatch.model.Team
import org.springframework.batch.item.ItemWriter
import org.springframework.stereotype.Component

@Component
class SportradarTeamWriter : ItemWriter<List<Team>> {
    override fun write(teams: MutableList<out List<Team>>) {
        logger().info(">>>>>>>>>>>>>>>>>>>>>>>> Sportradar Writer")
    }
}
