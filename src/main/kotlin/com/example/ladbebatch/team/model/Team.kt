package com.example.ladbebatch.team.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Column
import org.hibernate.annotations.DynamicUpdate
import java.util.Objects

@Entity
@DynamicUpdate
class Team (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    @Column(name = "team_id", unique = true)
    val teamId: String = "",
    val name: String = "",
    val market: String = "",
    @Column(name = "full_name")
    val fullName: String = "",
    val alias: String = "",
    val reference: String = "",
    @Column(name = "sr_id")
    val srId: String = "",
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Team

        return when {
            teamId != other.teamId -> false
            name != other.name -> false
            market != other.market -> false
            fullName != other.fullName -> false
            alias != other.alias -> false
            reference != other.reference -> false
            srId != other.srId -> false
            else -> true
        }
    }

    override fun hashCode(): Int {
        return Objects.hash(teamId, name, market, fullName, alias, reference, srId)
    }
}
