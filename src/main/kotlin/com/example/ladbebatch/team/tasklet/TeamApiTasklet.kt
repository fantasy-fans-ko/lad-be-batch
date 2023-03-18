package com.example.ladbebatch.team.tasklet

import com.example.ladbebatch.common.DataListShareBean
import com.example.ladbebatch.team.dto.team.TeamsApi
import com.example.ladbebatch.common.RestTemplateClient
import com.example.ladbebatch.common.logger
import com.example.ladbebatch.model.Team
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

@Component
class TeamApiTasklet(
    @Value("\${open-api.teams}") val apiUrl: String,
    @Value("\${open-api.nba-key}") val apiKey: String,
    val dataListShareBean: DataListShareBean<Team>
) : Tasklet, RestTemplateClient() {

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus {
        logger().info(">>>>>>>>>>>>>>>> TeamApiTasklet 실행 시작 <<<<<<<<<<<<<<<<<<<")
        val year: String = chunkContext.stepContext.jobParameters["year"].toString()
        val season: String = chunkContext.stepContext.jobParameters["season"].toString()

        val uri: URI = UriComponentsBuilder
            .fromUriString("$apiUrl/$year/$season/rankings.json")
            .queryParam("api_key", apiKey).encode().build().toUri()
        val externalData = getData<TeamsApi>(uri.toString())

        val teamDto = mutableListOf<Team>()

        externalData.conferences.map { conference ->
            conference.divisions.map { division ->
                division.teamApis.map { teamApi ->
                    teamDto.add(
                        Team(
                            teamId = teamApi.id,
                            name = teamApi.name,
                            market = teamApi.market,
                            fullName = "${teamApi.name} ${teamApi.market}",
                            region = division.name,
                            conferenceRanking = teamApi.rank.conference
                        )
                    )
                }
            }
        }

        dataListShareBean.putListData("teamDtoList", teamDto) // Bean에 저장

        logger().info(">>>>>>>>>>>>>>>> Team 정보 저장 성공 <<<<<<<<<<<<<<<<<<<")

        return RepeatStatus.FINISHED
    }

}
