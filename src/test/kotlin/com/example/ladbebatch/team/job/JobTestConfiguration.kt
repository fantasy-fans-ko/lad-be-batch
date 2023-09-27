package com.example.ladbebatch.team.job

import com.example.ladbebatch.common.bean.DataListShareBean
import com.example.ladbebatch.config.TestConfig
import com.example.ladbebatch.team.model.Team
import com.example.ladbebatch.team.chunk.processor.TeamProcessor
import com.example.ladbebatch.team.chunk.reader.TeamReader
import com.example.ladbebatch.team.chunk.writer.TeamWriter
import com.example.ladbebatch.team.repository.TeamRepository
import com.example.ladbebatch.team.service.TeamService
import com.example.ladbebatch.team.sportrader.OpenApiService
import com.example.ladbebatch.team.tasklet.CompareTeamTasklet
import com.example.ladbebatch.team.tasklet.TeamsTasklet
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.batch.test.context.SpringBatchTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.client.RestTemplate

@RunWith(SpringRunner::class)
@SpringBatchTest
@SpringBootTest(
    classes = [
        Team::class,
        TeamsTasklet::class,
        UpdateDataJob::class,
        TeamService::class,
        OpenApiService::class,
        TestConfig::class,
        DataListShareBean::class,
        TeamRepository::class,
        RestTemplate::class,
        CompareTeamTasklet::class,
        TeamReader::class,
        TeamProcessor::class,
        TeamWriter::class,
    ]
)
@ActiveProfiles("test")
class JobTestConfiguration {
    @Autowired
    lateinit var jobLauncherTestUtils: JobLauncherTestUtils

    @Test
    fun `DB에서 데이터 가져오는거 확인`() {
        val increment = RunIdIncrementer()
        val jobParameters: JobParameters = JobParametersBuilder()
            .addString("number3", increment.toString())
//            .addString("year", "2021")
//            .addString("season","REG")
            .toJobParameters()

        val jobExecution: JobExecution = jobLauncherTestUtils.launchJob(jobParameters)

        Assert.assertEquals("COMPLETED", jobExecution.exitStatus.exitCode)
    }
}
