package com.example.ladbebatch.team.chunk.process

import com.example.ladbebatch.common.DataShareBean
import com.example.ladbebatch.common.SuperStepExecution
import com.example.ladbebatch.common.logger
import com.example.ladbebatch.model.Team
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.annotation.BeforeStep
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.ItemProcessor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
@StepScope
class TeamProcessor(
    @Autowired val dataShareBean: DataShareBean<List<Team>>
): ItemProcessor<Team, Team>, SuperStepExecution<String>() {

    lateinit var teamList:  List<Team>
//    var isRead = true;

//    @PostConstruct
//    fun init() {
//        isRead = false;
//    }

    override fun process(team: Team): Team {

        val filter = teamList.filterNot { it == team }

        logger().info(">>>>>>>>>>>>>>>> Processor : ${team.name}")

        return team
    }

    @BeforeStep
    fun getDataShareBean(stepExecution: StepExecution) {
        this.teamList = dataShareBean.getData("teamList")
    }
}
