package com.example.ladbebatch.team.dto.team

data class Division(
    val alias: String = "",
    val id: String = "",
    val name: String = "",
    val teamApis: List<TeamApi>,
)
