package com.elyric.ricledger.data.mapper

import com.elyric.ricledger.data.local.entity.BillStoreEntity
import com.elyric.ricledger.domain.model.Bill
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
fun BillStoreEntity.toDomainModel(): Bill {

    val date = Date(this.time)

    return Bill(
        id = this.id,
        date = dateFormat.format(date),
        time = timeFormat.format(date),
        title = this.title,
        money = this.money,
        info = this.info,
        tag = this.tag
    )
}
val dateTimeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
fun Bill.toEntity(): BillStoreEntity {
    val timestamp = try {
        dateTimeFormat
            .parse("${this.date} ${this.time}")
            ?.time ?: System.currentTimeMillis()
    } catch (e: Exception) {
        System.currentTimeMillis()
    }
    return BillStoreEntity(
        time = timestamp,
        title = this.title,
        money = this.money,
        info = this.info,
        tag = this.tag
    )
}