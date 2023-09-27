package com.example.ladbebatch.common.model

import com.example.ladbebatch.common.model.enumeration.PlayerStatus
import com.example.ladbebatch.team.model.Team
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Enumerated
import jakarta.persistence.Column
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.ManyToOne
import jakarta.persistence.JoinColumn
import jakarta.persistence.FetchType.LAZY
import jakarta.persistence.OneToOne

@Entity
class Player(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    @Column(name = "player_id", unique = true)
    val playerId: String = "",
    @Column(name = "first_name")
    val firstName: String = "",
    @Column(name = "last_name")
    val lastName: String = "",
    @Column(name = "full_name")
    val fullName: String = "",
    @Column(name = "abbr_name")
    val abbrName: String = "",
    @Enumerated(STRING)
    val status: PlayerStatus = PlayerStatus.HEALTHY,
    val position: String = "",
    @Column(name = "primary_position")
    val primaryPosition: String = "",
    @Column(name = "sr_id")
    val srId: String ="",
    @ManyToOne(fetch = LAZY) @JoinColumn(name = "team_id")
    var team: Team,
    @OneToOne(fetch = LAZY) @JoinColumn(name = "injury_id")
    var injury: Injury,
) {

}
