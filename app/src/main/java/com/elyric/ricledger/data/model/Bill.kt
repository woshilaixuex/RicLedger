package com.elyric.ricledger.data.model


data class Bill (
    val id : Long, // id = date + time + list_id
    val date: Long,
    val time: Long,
    val title: String,
    val money: Double,
    val info: String?,
    val tag: String?, // tag格式为 option1:tag1,tag2;option2:tag1,tag2
)


