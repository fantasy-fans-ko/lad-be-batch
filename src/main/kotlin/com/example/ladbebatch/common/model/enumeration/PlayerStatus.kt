package com.example.ladbebatch.common.model.enumeration

enum class PlayerStatus(
    val desc: String = "",
) {
    INJURED("부상"),
    OUT("경기 미출전"),
    GAME_TIME_DECISION("경기 출전 가능성 있음"),
    HEALTHY("경기 출전")
}
