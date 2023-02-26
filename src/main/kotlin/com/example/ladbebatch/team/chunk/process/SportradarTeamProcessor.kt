package com.example.ladbebatch.team.chunk.process

import com.example.ladbebatch.common.SuperStepExecution
import com.example.ladbebatch.model.Team
import com.example.ladbebatch.team.dto.team.TeamApi
import com.example.ladbebatch.team.dto.team.TeamsApi
import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component

@Component
class SportradarTeamProcessor : SuperStepExecution<Team>(), ItemProcessor<TeamsApi, List<Team>>{

    override fun process(teamsApi: TeamsApi): List<Team> {
//        val teamApis = mutableListOf<TeamApi>()
//        teamsApi.conferences.map { it.divisions.map { division -> division.teamApis.map { team -> teamApis.add(team) } } }
//        val teamList : List<Team> = teamApis.map {
//            Team(
//                teamId = it.id,
//                name = it.name,
//                market = it.market,
//                fullName = "${it.name} ${it.market}"
//            )
//        }.toList()
//
//        return teamList
        return emptyList()
    }

    fun compareToTeam() {

    }
}
