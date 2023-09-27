package com.example.ladbebatch.team.dto.teams

data class Venue(
    val address: String,
    val capacity: Int,
    val city: String,
    val country: String,
    val id: String,
    val location: Location,
    val name: String,
    val sr_id: String,
    val state: String,
    val zip: String
)
