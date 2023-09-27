package com.example.ladbebatch.common.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Column

@Entity
class Injury (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    @Column(name = "injury_id", unique = true)
    val injuryId: String = "",
    val comment: String = "",
    val description: String = "",
    val status: String = "",
    val startDate: String = "",
    val updateDate: String = "",
    ){

}
