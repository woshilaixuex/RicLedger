package com.elyric.ricledger.data.mapper

import com.elyric.ricledger.data.local.entity.BillStoreEntity
import com.elyric.ricledger.domain.model.Bill


fun BillStoreEntity.toDomainModel(): Bill {
    return Bill(
        id = id,
        date = date,
        time = time,
        title = title,
        money = money,
        info = info,
        tag = tag,
    )
}

fun Bill.toEntity(): BillStoreEntity {
    return BillStoreEntity(
        id = id,
        date = date,
        time = time,
        title = title,
        money = money,
        info = info,
        tag = tag,
    )
}