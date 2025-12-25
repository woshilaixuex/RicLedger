package com.elyric.ricledger.data.model


data class Bill (
    val title: String,
    val tag: Tag = Tag.UNKNOW,
    val money: Double = 0.0,
    val info: String = "",
    val time: Long = 0L,
)


