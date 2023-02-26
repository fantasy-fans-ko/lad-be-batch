package com.example.ladbebatch.team.job

import com.example.ladbebatch.common.logger
import com.example.ladbebatch.model.Team
import com.example.ladbebatch.team.chunk.process.SportradarTeamProcessor
import com.example.ladbebatch.team.chunk.process.TeamProcessor
import com.example.ladbebatch.team.chunk.reader.SportradarTeamReader
import com.example.ladbebatch.team.chunk.reader.TeamReader
import com.example.ladbebatch.team.chunk.writer.SportradarTeamWriter
import com.example.ladbebatch.team.chunk.writer.TeamWriter
import com.example.ladbebatch.team.dto.team.TeamsApi
import com.example.ladbebatch.team.tasklet.GetTeamTasklet
import com.example.ladbebatch.team.tasklet.TeamApiTasklet
import com.example.ladbebatch.team.tasklet.TestTasklet
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.listener.ExecutionContextPromotionListener
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class TeamJobConfiguration(
    val jobBuilderFactory: JobBuilderFactory,
    val stepBuilderFactory: StepBuilderFactory,
//    val getTeamTasklet: GetTeamTasklet,
//    val teamApiTasklet: TeamApiTasklet,
    val teamReader: TeamReader,
    val teamProcessor: TeamProcessor,
    val teamWriter: TeamWriter,
//    val testTasklet: TestTasklet,
    val sportradarTeamReader: SportradarTeamReader,
//    val sportradarTeamProcessor: SportradarTeamProcessor,
    val sportradarTeamWriter: SportradarTeamWriter,
) {

    // Cursor JDBC
//    @Bean
//    fun jdbcCursorItemReaderJob(): Job {
//        return jobBuilderFactory.get("jdbcCursorItemReaderJob")
//            .start(jdbcCursorItemReaderStep())
//            .build()
//    }

    @Bean
    fun getTeamJob() : Job {
        return jobBuilderFactory.get("getTeam")
            .start(getTeamOfExternalApiChunkStep())
            .next(getTeamOfDbStep())
//            .next(getTestTasklet())
            .build()
    }

//    @Bean
//    fun getTestTasklet() : Step {
//        return stepBuilderFactory.get("testTasklet")
//            .tasklet(testTasklet)
//            .listener(promotionListener())
//            .build()
//    }

//    @Bean
//    fun getDBTeamStep() : Step {
//        return stepBuilderFactory.get("getTeamStep")
//            .tasklet(getTeamTasklet)
//            .listener(promotionListener())
//            .build()
//    }

    @Bean
    fun getTeamOfDbStep() : Step {
        return stepBuilderFactory.get("getTeamOfDbStep")
            .chunk<Team, Team>(10)
            .reader(teamReader.getTeamAll())
            .processor(teamProcessor)
            .writer(teamWriter)
            .listener(promotionListener())
            .build()
    }

    @Bean
    fun getTeamOfExternalApiChunkStep() : Step {
        return stepBuilderFactory.get("getTeamOfExternalApiChunkStep")
            .chunk<TeamsApi, List<Team>>(1)
            .reader(sportradarTeamReader)
            .writer(sportradarTeamWriter)
            .listener(promotionListener())
            .build()
    }


//    @Bean
//    fun getTeamOfExternalApiStep() : Step {
//        return stepBuilderFactory.get("getTeamOfExternalApiStep")
//            .tasklet(teamApiTasklet)
//            .listener(promotionListener())
//            .build()
//    }

//    @Bean
//    fun jdbcCursorItemReaderStep(): Step {
//        return stepBuilderFactory.get("jdbcCursorItemStep")
//            .chunk<Team, Team>(10)
//            .reader(jpaPagingItemReader())
//            .writer(jdbcCursorItemWriter())
//            .build()
//    }
//
//    @Bean
//    fun jpaPagingItemReader(): JpaPagingItemReader<Team> {
//        return JpaPagingItemReaderBuilder<Team>()
//            .name("JpaPagingItemReader")
//            .entityManagerFactory(entityManagerFactory)
//            .pageSize(2)
//            .queryString("SELECT t FROM Team t")
//            .build()
//    }
//
//    private fun jdbcCursorItemWriter(): ItemWriter<Team> {
//        return ItemWriter { items ->
//            items.stream().forEach {
//                logger().info(it.name)
//                logger().info("team : $it")
//            }
//        }
//    }

    @Bean
    fun promotionListener(): ExecutionContextPromotionListener {
        val executionContextPromotionListener = ExecutionContextPromotionListener()
        // 데이터 공유를 위해 사용될 key값을 미리 빈에 등록해주어야 합니다.
        executionContextPromotionListener.setKeys(arrayOf("TEAM"))
        return executionContextPromotionListener
    }
}
