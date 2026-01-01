package com.elyric.ricledger.data.repository

import android.content.Context
import com.elyric.ricledger.common.TimeHelper
import com.elyric.ricledger.data.local.AppDataBase
import com.elyric.ricledger.data.local.dao.BillStoreDao
import com.elyric.ricledger.data.mapper.toDomainModel
import com.elyric.ricledger.data.mapper.toEntity
import com.elyric.ricledger.domain.model.Bill

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
    suspend fun addBill(bill: Bill){
        val entity = bill.toEntity()
        dao.insertBill(entity)
    }
    companion object {
        @Volatile
        private var INSTANCE: BillStoreRepository? = null
        fun getInstance(context: Context): BillStoreRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildRepository(context).also {
                    INSTANCE = it
                }
            }
        }
        private fun buildRepository(context: Context): BillStoreRepository {
            val database = AppDataBase.getDatabase(
                context.applicationContext
            )
            return BillStoreRepository(database.BillStoreDao())
        }
    }
}