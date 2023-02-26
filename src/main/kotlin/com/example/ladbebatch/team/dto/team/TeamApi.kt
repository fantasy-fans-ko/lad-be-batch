package com.example.ladbebatch.team.dto.team

data class TeamApi(
    val id: String = "",
    val market: String = "",
    val name: String = "",
    val rank: Rank,
    val reference: String = "",
    val sr_id: String = "",
)
