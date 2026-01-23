package com.elyric.ricledger.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(
    tableName = "bill_store",
    indices = [
        Index(value = ["time"]),   // 按日期建索引
    ]
)
@Serializable
data class BillStoreEntity (
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val time: Long,
    val title: String,
    val money: Double,
    val info: String?,
    val tag: String?, // tag格式为 option1:tag1,tag2;option2:tag1,tag2
)