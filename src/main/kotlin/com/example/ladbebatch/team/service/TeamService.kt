package com.example.ladbebatch.team.service

import com.example.ladbebatch.team.model.Team
import com.example.ladbebatch.team.repository.TeamRepository
import org.springframework.stereotype.Service

@Service
class TeamService(
    val teamRepository: TeamRepository,
){
    fun saveOneTeam(team: Team): Team = teamRepository.save(team)

    fun saveAllTeams(teams: MutableList<Team>) {
        teamRepository.saveAll(teams)
    }

    fun existOneTeam(): Boolean = teamRepository.existsById(1L)

    fun deleteOneTeam(team: Team) {
        teamRepository.delete(team)
    }

    fun getAllTeam(): List<Team> = teamRepository.findAll()

}
