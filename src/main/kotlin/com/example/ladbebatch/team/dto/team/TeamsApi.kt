package com.example.ladbebatch.team.dto.team

data class TeamsApi(
    val conferences: List<Conference>,
    val league: League,
    val season: Season,
)
