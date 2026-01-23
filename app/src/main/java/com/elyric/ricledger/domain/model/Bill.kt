package com.elyric.ricledger.domain.model


data class Bill (
    val id : Long, // id 自增
    val date: String,  // yyyy-MM-dd
    val time: String, // HH:mm
    val title: String,
    val money: Double,
    val info: String?,
    val tag: String?, // tag格式为 option1:tag1,tag2;option2:tag1,tag2
)


