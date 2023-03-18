package com.example.ladbebatch.model

import com.example.ladbebatch.model.enumeration.PlayerStatus
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Column
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated
import javax.persistence.ManyToOne
import javax.persistence.FetchType.LAZY
import javax.persistence.JoinColumn

@Entity(name = "players")
@Table(name = "players")
class Player(
    @Id @Column(name = "player_id")
    val playerId: Long = 0L,
    @Column(length = 16)
    val name: String = "",
    @Column(length = 4)
    var position: String = "",
    @Column(name = "three_pct")
    var threePct: Float = 0F,
    @Column(name = "ft_pct")
    var ftPct: Float = 0F,
    @Column(name = "fg_pct")
    var fgPct: Float = 0F,
    var point: Int = 0,
    var rebound: Int = 0,
    var assist: Int = 0,
    var steal: Int = 0,
    var block: Int = 0,
    @Column(name = "turn_over")
    var turnOver: Int = 0,
    @Column(name = "triple_double")
    var tripleDouble: Int = 0,
    @Enumerated(STRING)
    var status: PlayerStatus = PlayerStatus.HEALTHY,
    @Column(name = "image_path")
    var imagePath: String = "",
    @Column(name = "rank_pre")
    var rankPre: Int = 0,
    @Column(name = "rank_current")
    var rankCurrent: Int = 0,
    @ManyToOne(fetch = LAZY) @JoinColumn(name = "team_id")
    var team: Team
) {

}
