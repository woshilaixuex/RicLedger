package com.elyric.ricledger.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elyric.ricledger.data.local.entity.BillStoreEntity

@Dao
interface BillStoreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBill(billStoreEntity: BillStoreEntity)
    @Query("SELECT * FROM bill_store")
    suspend fun getAllBillList(): List<BillStoreEntity>
    @Query("SELECT * FROM bill_store  WHERE time = :today")
    suspend fun getBillListByDate(today: Long): List<BillStoreEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBills(bills: List<BillStoreEntity>)
}