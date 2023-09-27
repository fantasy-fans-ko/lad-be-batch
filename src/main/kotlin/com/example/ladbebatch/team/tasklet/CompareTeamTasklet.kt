package com.example.ladbebatch.team.tasklet

import com.example.ladbebatch.common.bean.DataListShareBean
import com.example.ladbebatch.team.model.Team
import com.example.ladbebatch.team.service.TeamService
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.StepExecutionListener
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.stereotype.Component

@Component
class CompareTeamTasklet(
    val dataListShareBean: DataListShareBean<Team>,
    val teamService: TeamService,
): Tasklet, StepExecutionListener {
    lateinit var openApiTeams: MutableList<Team>

    override fun beforeStep(stepExecution: StepExecution) {
        openApiTeams = dataListShareBean.getDataList("openApiTeams")
    }

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus {
        if (openApiTeams.isNotEmpty())
            teamService.saveAllTeams(openApiTeams)

        return RepeatStatus.FINISHED
    }

    override fun afterStep(stepExecution: StepExecution): ExitStatus {
        return stepExecution.exitStatus
    }
}
