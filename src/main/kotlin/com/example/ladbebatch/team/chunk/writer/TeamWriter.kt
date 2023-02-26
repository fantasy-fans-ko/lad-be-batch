package com.example.ladbebatch.team.chunk.writer

import com.example.ladbebatch.common.logger
import com.example.ladbebatch.model.Team
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.StepExecutionListener
import org.springframework.batch.item.ItemWriter
import org.springframework.stereotype.Component

@Component
class TeamWriter: ItemWriter<Team>, StepExecutionListener {

    override fun beforeStep(stepExecution: StepExecution) {
    }

    override fun write(items: MutableList<out Team>) {
        logger().info(">>>>>>>>>>>>>>>> Writer")
    }

    override fun afterStep(stepExecution: StepExecution): ExitStatus? {
        return null
    }
}
