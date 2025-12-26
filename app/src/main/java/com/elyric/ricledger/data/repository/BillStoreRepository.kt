package com.elyric.ricledger.data.repository

import com.elyric.ricledger.common.TimeHelper
import com.elyric.ricledger.data.local.dao.BillStoreDao
import com.elyric.ricledger.data.mapper.toDomainModel
import com.elyric.ricledger.data.model.Bill

/**
 * @exception: 仓储层，主要做持久化与业务层之间的数据处理
 */
class BillStoreRepository(
    private val dao: BillStoreDao
) {
    suspend fun getBillListWithTodayTime(): List<Bill> {
        val todayMillis = TimeHelper.getTodayStartMillis()
        val entities  = dao.getBillListByDate(todayMillis)
        return entities.map { it.toDomainModel() }
    }
}