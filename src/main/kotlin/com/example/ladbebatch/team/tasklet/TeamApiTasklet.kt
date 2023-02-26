package com.example.ladbebatch.team.tasklet

import com.example.ladbebatch.common.DataShareBean
import com.example.ladbebatch.team.dto.team.TeamsApi
import com.example.ladbebatch.common.RestTemplateClient
import com.example.ladbebatch.model.Team
import com.example.ladbebatch.team.dto.team.TeamApi
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.StepExecutionListener
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

@Component
@StepScope
class TeamApiTasklet(
    @Value("\${open-api.teams}") val apiUrl: String,
    @Value("\${open-api.nba-key}") val apiKey: String,
    @Value("#{jobParameters['year']}") val year: String,
    @Value("#{jobParameters['season']}") val season: String,
    @Autowired val dataShareBean: DataShareBean<List<Team>>
) : Tasklet, RestTemplateClient(), StepExecutionListener {

    override fun beforeStep(stepExecution: StepExecution) {
    }

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus {
        val uri: URI = UriComponentsBuilder
            .fromUriString("$apiUrl/$year/$season/rankings.json")
            .queryParam("api_key", apiKey).encode().build().toUri()
        val teamsApi = getData<TeamsApi>(uri.toString())

        val teamApis = mutableListOf<TeamApi>()
        teamsApi.conferences.map { it.divisions.map { division -> division.teamApis.map { team -> teamApis.add(team) } } }
        val teamList : List<Team> = teamApis.map {
            Team(
                teamId = it.id,
                name = it.name,
                market = it.market,
                fullName = "${it.name} ${it.market}"
            )
        }.toList()

        dataShareBean.putData("teamList", teamList)

        return RepeatStatus.FINISHED
    }

    override fun afterStep(stepExecution: StepExecution): ExitStatus {
        return ExitStatus.COMPLETED
    }
}
