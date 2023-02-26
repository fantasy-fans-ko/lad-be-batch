package com.example.ladbebatch.team.job

import com.example.ladbebatch.config.TestConfig
import com.example.ladbebatch.model.Team
import com.example.ladbebatch.repository.TeamRepository
import com.example.ladbebatch.team.chunk.process.TeamProcessor
import com.example.ladbebatch.team.chunk.reader.SportradarTeamReader
import com.example.ladbebatch.team.chunk.reader.TeamReader
import com.example.ladbebatch.team.chunk.writer.SportradarTeamWriter
import com.example.ladbebatch.team.chunk.writer.TeamWriter
import com.example.ladbebatch.team.tasklet.GetTeamTasklet
import com.example.ladbebatch.team.tasklet.TestTasklet
import org.junit.Assert
import org.junit.Test
import org.junit.Before
import org.junit.jupiter.api.BeforeEach
import org.junit.runner.RunWith
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.batch.test.context.SpringBatchTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBatchTest
@SpringBootTest(
    classes = [
        TestConfig::class,
        TeamJobConfiguration::class,
        Team::class,
        TeamReader::class,
        TeamProcessor::class,
        TeamWriter::class,
        SportradarTeamReader::class,
        SportradarTeamWriter::class,
    ]
)
@ActiveProfiles("local")
class JobTestConfiguration {
    @Autowired
    lateinit var jobLauncherTestUtils: JobLauncherTestUtils

    @Autowired
    lateinit var teamRepository: TeamRepository

//    @Before
//    fun init() {
//        val team = Team(
//            id = 1,
//            teamId = "1",
//            name = "브루클린",
//            market = "네츠",
//            fullName = "브루클린 네츠"
//        )
//        teamRepository.save(team)
//    }

    @Test
    fun `DB에서 데이터 가져오는거 확인`() {

        val jobParameters: JobParameters = JobParametersBuilder()
            .addString("test", "9")
            .toJobParameters()

        val jobExecution: JobExecution = jobLauncherTestUtils.launchJob(jobParameters)

        Assert.assertEquals("COMPLETED", jobExecution.exitStatus.exitCode)
    }
}
