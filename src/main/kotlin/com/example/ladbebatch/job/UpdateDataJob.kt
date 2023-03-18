package com.example.ladbebatch.job

import com.example.ladbebatch.team.tasklet.TeamApiTasklet
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UpdateDataJob(
    val jobBuilderFactory: JobBuilderFactory,
    val stepBuilderFactory: StepBuilderFactory,
    val teamApiTasklet: TeamApiTasklet,
) {
    @Bean
    fun getTeamJob() : Job {
        return jobBuilderFactory.get("getTeam")
            .start(getTeamOfExternalApiTasklet())
//            .next(getTeamOfDbStep())
            .build()
    }

    @Bean
    fun getTeamOfExternalApiTasklet() : Step {
        return stepBuilderFactory.get("getTeamOfExternalApi")
            .tasklet(teamApiTasklet)
            .build()
    }
}
