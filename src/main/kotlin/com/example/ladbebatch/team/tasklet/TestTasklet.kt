package com.example.ladbebatch.team.tasklet

import com.example.ladbebatch.common.SuperStepExecution
import com.example.ladbebatch.common.logger
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.annotation.BeforeStep
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.stereotype.Component

@Component
class TestTasklet : Tasklet, SuperStepExecution<String>() {
    lateinit var data : String

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus {
        logger().info(">>>>>>>>>>>>>>>> data : ${this.data}")
        return RepeatStatus.FINISHED
    }

    @BeforeStep
    fun retrieveInterstepData(stepExecution: StepExecution) {
        // step 실행전 stepExecution을 가져와 등록한 회원 정보를 가져온다.
        super.setStepExecution(stepExecution)
        this.data = super.getData("TEAM") as String
    }
}
