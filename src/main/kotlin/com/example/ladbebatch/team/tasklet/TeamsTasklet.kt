package com.example.ladbebatch.team.tasklet

import com.example.ladbebatch.common.bean.DataListShareBean
import com.example.ladbebatch.team.model.Team
import com.example.ladbebatch.team.service.TeamService
import com.example.ladbebatch.team.sportrader.OpenApiService
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.stereotype.Component

@Component
class TeamsTasklet(
    val openApiService: OpenApiService,
    val teamService: TeamService,
    val dataListShareBean: DataListShareBean<Team>
) : Tasklet {

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus {
        val teams = openApiService.getAllTeams()
        dataListShareBean.init("openApiTeams", teams)
        return RepeatStatus.FINISHED
    }
}
