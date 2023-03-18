package com.example.ladbebatch.repository

import com.example.ladbebatch.model.Player
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlayerRepository: JpaRepository<Player, String>
