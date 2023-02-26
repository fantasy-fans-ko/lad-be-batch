package com.example.ladbebatch.team.tasklet

import com.example.ladbebatch.model.Team
import com.example.ladbebatch.repository.TeamRepository
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.stereotype.Component

@StepScope
@Component
class GetTeamTasklet(
    val teamRepository: TeamRepository,
) : Tasklet {
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus {
        val teams : MutableList<Team> = teamRepository.findAll()

        if (teams.isEmpty())
            contribution.exitStatus = ExitStatus.FAILED
        else {
            chunkContext.setAttribute("Team", teams)
            contribution.exitStatus = ExitStatus.COMPLETED
        }

        return RepeatStatus.FINISHED
    }
}
