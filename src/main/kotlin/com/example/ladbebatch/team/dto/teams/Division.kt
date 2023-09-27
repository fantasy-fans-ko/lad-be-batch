package com.example.ladbebatch.team.dto.teams

data class Division(
    val alias: String,
    val id: String,
    val name: String,
    val teams: List<Team>
)
