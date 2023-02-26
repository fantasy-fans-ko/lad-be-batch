package com.example.ladbebatch.common

import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.StepExecution
import org.springframework.batch.item.ExecutionContext

open class SuperStepExecution <T> {
    private var stepExecution: StepExecution? = null

    protected fun putData(key : String, data : T) {
        val stepContext : ExecutionContext = stepExecution?.executionContext ?: throw NullPointerException("StepExecution is Null")
        stepContext.put(key, data)
    }

    protected fun getData(key: String): Any? {
        val jobExecution: JobExecution = stepExecution?.jobExecution ?: throw NullPointerException("StepExecution is Null")
        val jobContext: ExecutionContext = jobExecution.executionContext
        return jobContext[key]
    }

    protected fun setStepExecution(stepExecution: StepExecution) {
        this.stepExecution = stepExecution
    }

    protected fun getStepExecution(): StepExecution {
        return stepExecution ?: throw NullPointerException("StepExecution is Null")
    }
}
