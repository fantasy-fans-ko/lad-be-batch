package com.example.ladbebatch.team.processor

import com.example.ladbebatch.common.DataListShareBean
import com.example.ladbebatch.model.Team
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.StepExecutionListener
import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component

@Component
class TeamProcessor(
    val dataListShareBean: DataListShareBean<Team>
) : ItemProcessor<Team, Team>, StepExecutionListener {
    lateinit var teamDtoList: List<Team>

    override fun beforeStep(stepExecution: StepExecution) {
        teamDtoList = dataListShareBean.getData("teamDtoList")
    }

    override fun process(item: Team): Team {
        TODO("Not yet implemented")
    }

    override fun afterStep(stepExecution: StepExecution): ExitStatus {
        TODO("Not yet implemented")
    }
}
