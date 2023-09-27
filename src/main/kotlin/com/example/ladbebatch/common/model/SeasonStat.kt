package com.example.ladbebatch.common.model

import com.example.ladbebatch.common.model.enumeration.SeasonType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.Column
import jakarta.persistence.ManyToOne
import jakarta.persistence.JoinColumn
import jakarta.persistence.FetchType.LAZY
import jakarta.persistence.OneToOne

@Table(name = "season_stat")
@Entity(name = "season_stat")
class SeasonStat (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    @Column(name = "stat_id", unique = true)
    val statId: String = "",
    val year: Int = 0,
    @Column(name="season_type")
    val seasonType: SeasonType = SeasonType.REG,
    @ManyToOne(fetch = LAZY) @JoinColumn(name = "player_id")
    var player: Player,
    @OneToOne(fetch = LAZY) @JoinColumn(name = "player_avg_stat_id")
    var playerAvgStat: PlayerAvgStat,
    @OneToOne(fetch = LAZY) @JoinColumn(name = "player_total_stat_id")
    var playerTotalStat: PlayerTotalStat,
        ){
}
