package com.example.ladbebatch.team.chunk.processor

import com.example.ladbebatch.common.bean.DataListShareBean
import com.example.ladbebatch.common.logger
import com.example.ladbebatch.team.model.Team
import com.example.ladbebatch.team.service.TeamService
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.StepExecutionListener
import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component

@Component
class TeamProcessor(
    val dataListShareBean: DataListShareBean<Team>,
    val teamService: TeamService,
) : ItemProcessor<Team, Team>, StepExecutionListener {
    lateinit var openApiTeams: MutableList<Team>
    override fun beforeStep(stepExecution: StepExecution) {
        openApiTeams = dataListShareBean.getDataList("openApiTeams")
    }

    /**
     * openApiTeams 에
     * 1. team 에 있는 경우
     *      -> team 과 객체 내부의 값이 다른 경우 (업데이트가 있음 -> update 쿼리 필요)
     * 2. team 이 없는 경우
     *      -> team 이 없어지고 새로운 team 이 생성된 경우 (delete 후 insert)
     */
    override fun process(team: Team): Team? {
        val findTeam: Team? = openApiTeams.find { openApiTeam -> openApiTeam.teamId == team.teamId }

        if (findTeam == null) { // team 이 없는 경우
            teamService.deleteOneTeam(team) // team 을 삭제하고
            // openApiTeam 에 존재하는 team 을 저장
            return null
        } else {
            logger().info("team 의 id 값 : {}, findTeam 의 id 값 : {}", team.id, findTeam.id)

            if (team == findTeam) {// 객체 내부 값이 같으면 패스
                logger().info("똑같다.")
                dataListShareBean.removeItem("openApiTeams", team)
                return null
            }else {
                logger().info("다르다.")
            }
        }

        return findTeam
    }

    override fun afterStep(stepExecution: StepExecution): ExitStatus {
        return ExitStatus.COMPLETED
    }
}
