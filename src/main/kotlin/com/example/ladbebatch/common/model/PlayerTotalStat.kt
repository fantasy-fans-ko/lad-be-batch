package com.example.ladbebatch.common.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.Column

@Table(name = "player_total_stat")
@Entity(name = "player_total_stat")
class PlayerTotalStat (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    @Column(name = "total_id", unique = true)
    val totalId: String = "",
    @Column(name = "field_goals_att")
    val fga: Int = 0,
    @Column(name = "field_goals_made")
    val fgm: Int = 0,
    @Column(name = "field_goals_pct")
    val fgp: Double = 0.0,
    @Column(name = "three_points_att")
    val tpa: Int = 0,
    @Column(name = "three_points_made")
    val tpm: Int = 0,
    @Column(name = "three_points_pct")
    val tpp: Double = 0.0,
    @Column(name = "free_throws_att")
    val fta: Int = 0,
    @Column(name = "free_throws_made")
    val ftm: Int = 0,
    @Column(name = "free_throws_pct")
    val ftp: Double = 0.0,

    val points: Int = 0,
    val rebounds: Int = 0,
    val steals: Int = 0,
    val assists: Int = 0,
    val blocks: Int = 0,
    val turnovers: Int = 0,
    @Column(name = "triple_doubles")
    val tripleDoubles: Int = 0,
    @Column(name = "personal_fouls")
    val personalFouls: Int = 0,
    @Column(name = "tech_fouls")
    val techFouls: Int = 0,
    @Column(name = "flagrant_fouls")
    val flagrantFouls: Int = 0,
    @Column(name = "fouls_drawn")
    val foulsDrawn: Int = 0,
        ){
}
