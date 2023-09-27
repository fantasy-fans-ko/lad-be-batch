package com.example.ladbebatch.player.repository

import com.example.ladbebatch.common.model.Player
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlayerRepository: JpaRepository<Player, String>
