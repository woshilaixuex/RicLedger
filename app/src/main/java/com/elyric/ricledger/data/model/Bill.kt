package com.elyric.ricledger.data.model


data class Bill (
    val tag: String = "未知",
    val money: Double = 0.0,
    val info: String = "",
    val time: Long = 0L,
)


