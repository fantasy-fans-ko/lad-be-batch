package com.example.ladbebatch.team.chunk.writer

import com.example.ladbebatch.common.bean.DataListShareBean
import com.example.ladbebatch.team.model.Team
import com.example.ladbebatch.team.service.TeamService
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.StepExecutionListener
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter
import org.springframework.stereotype.Component

@Component
class TeamWriter(
    val teamService: TeamService,
    val dataListShareBean: DataListShareBean<Team>
) : ItemWriter<Team>, StepExecutionListener {
    var openApiTeamsSize: Int = 0

    override fun beforeStep(stepExecution: StepExecution) {
        openApiTeamsSize = dataListShareBean.getSize("openApiTeams")
    }

//    override fun write(teams: MutableList<out Team>) {
//        teamService.saveAllTeams(teams as MutableList<Team>)
//    }

    override fun afterStep(stepExecution: StepExecution): ExitStatus {
        if (openApiTeamsSize == 0)
            stepExecution.exitStatus = ExitStatus("EMPTY", "새로운 Team 은 없습니다.")
        else
            stepExecution.exitStatus = ExitStatus("NOT_EMPTY", "새로운 Team 이 있습니다.")

        return stepExecution.exitStatus
    }

    override fun write(chunk: Chunk<out Team>) {
        TODO("Not yet implemented")
    }
}

