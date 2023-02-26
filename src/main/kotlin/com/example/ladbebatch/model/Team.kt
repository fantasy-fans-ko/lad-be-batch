package com.example.ladbebatch.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.Table

@Entity(name = "teams")
@Table(name = "teams")
class Team (
    @Id @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0L,
    @Column(name = "team_id")
    val teamId: String = "",
    var name: String = "",
    val market: String = "",
    @Column(name = "full_name")
    var fullName: String = "",
)
