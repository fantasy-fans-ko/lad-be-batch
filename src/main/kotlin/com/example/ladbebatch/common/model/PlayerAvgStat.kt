package com.example.ladbebatch.common.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.Column

@Table(name = "player_avg_stat")
@Entity(name = "player_avg_stat")
class PlayerAvgStat (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    @Column(name = "avg_id", unique = true)
    val avgId: String = "",
    val points: Double = 0.0,
    val rebounds: Double = 0.0,
    val steals: Double = 0.0,
    val assists: Double = 0.0,
    val blocks: Double = 0.0,
    val turnovers: Double = 0.0,

    @Column(name = "field_goals_made")
    val fgm: Double = 0.0,
    @Column(name = "field_goals_att")
    val fga: Double = 0.0,
    @Column(name = "three_points_made")
    val tpm: Double = 0.0,
    @Column(name = "three_points_att")
    val tpa: Double = 0.0,
    @Column(name = "free_throws_made")
    val ftm: Double = 0.0,
    @Column(name = "free_throws_att")
    val fta: Double = 0.0,
    @Column(name = "offensive_fouls")
    val offensiveFouls: Double = 0.0,
    @Column(name = "flagrant_fouls")
    val flagrantFouls: Double = 0.0,
    @Column(name = "personal_fouls")
    val personalFouls: Double = 0.0,
    @Column(name = "fouls_drawn")
    val foulsDrawn: Double = 0.0,
        ){
}
