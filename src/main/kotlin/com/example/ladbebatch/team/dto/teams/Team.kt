package com.example.ladbebatch.team.dto.teams

data class Team(
    val alias: String,
    val id: String,
    val market: String,
    val name: String,
    val reference: String,
    val sr_id: String,
    val team_colors: List<TeamColor>,
    val venue: Venue
)
