package com.example.ladbebatch.model

import org.hibernate.annotations.DynamicUpdate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity(name = "teams")
@Table(name = "teams")
@DynamicUpdate
class Team (
    @Id @Column(name = "team_id")
    val teamId: String = "",
    var name: String = "",
    val market: String = "",
    @Column(name = "full_name")
    var fullName: String = "",
    val region: String = "",
    @Column(name = "conference_ranking")
    val conferenceRanking: Int = 0,
)
