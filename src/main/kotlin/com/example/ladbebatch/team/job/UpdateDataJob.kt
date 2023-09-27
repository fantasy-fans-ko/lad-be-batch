package com.example.ladbebatch.team.job

import com.example.ladbebatch.team.model.Team
import com.example.ladbebatch.team.chunk.processor.TeamProcessor
import com.example.ladbebatch.team.chunk.reader.TeamReader
import com.example.ladbebatch.team.chunk.writer.TeamWriter
import com.example.ladbebatch.team.tasklet.CompareTeamTasklet
import com.example.ladbebatch.team.tasklet.TeamsTasklet
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class UpdateDataJob(
    val platformTransactionManager: PlatformTransactionManager,
    val jobRepository: JobRepository,
    val teamsTasklet: TeamsTasklet,
    val compareTeamTasklet: CompareTeamTasklet,
    val teamReader: TeamReader,
    val teamProcessor: TeamProcessor,
    val teamWriter: TeamWriter,
) {
    @Bean
    fun getTeamJob() : Job {
        return JobBuilder("getTemJob", jobRepository)
            .start(getTeamsOfExternalApiTasklet())
            .next(getTeamOfDBStep())
                .on("EMPTY")
                .end()
            .from(getTeamOfDBStep())
                .on("NOT_EMPTY")
                .to(compareStep())
            .end()
            .build()
    }

    @Bean
    fun getTeamsOfExternalApiTasklet() : Step {
        return StepBuilder("getTeamsOfExternalApiTasklet", jobRepository)
            .tasklet(teamsTasklet, platformTransactionManager)
            .build()
    }

    @Bean
    fun compareStep() : Step {
        return StepBuilder("compare", jobRepository)
            .tasklet(compareTeamTasklet, platformTransactionManager)
            .build()
    }

    @Bean
    fun getTeamOfDBStep() : Step {
        return StepBuilder("getTeamOfDBStep", jobRepository)
            .chunk<Team, Team>(10, platformTransactionManager)
            .reader(teamReader.getAllTeams())
            .processor(teamProcessor)
            .writer(teamWriter)
            .build()
    }
}
