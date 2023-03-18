package com.example.ladbebatch.team.job

import com.example.ladbebatch.model.Team
import com.example.ladbebatch.repository.TeamRepository
import com.example.ladbebatch.team.tasklet.TeamApiTasklet
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.batch.test.context.SpringBatchTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBatchTest
@SpringBootTest(
    classes = [
        Team::class,
        TeamApiTasklet::class,
    ]
)
@ActiveProfiles("test")
class JobTestConfiguration {
    @Autowired
    lateinit var jobLauncherTestUtils: JobLauncherTestUtils

    @Autowired
    lateinit var teamRepository: TeamRepository

    @Test
    fun `DB에서 데이터 가져오는거 확인`() {

        val jobParameters: JobParameters = JobParametersBuilder()
            .addString("year", "2021")
            .addString("season","REG")
            .toJobParameters()

        val jobExecution: JobExecution = jobLauncherTestUtils.launchJob(jobParameters)

        Assert.assertEquals("COMPLETED", jobExecution.exitStatus.exitCode)
    }
}
