package com.example.ladbebatch.team.dto.teams

data class Conference(
    val alias: String,
    val divisions: List<Division>,
    val id: String,
    val name: String
)
