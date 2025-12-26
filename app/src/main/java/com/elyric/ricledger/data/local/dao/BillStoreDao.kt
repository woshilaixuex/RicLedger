package com.elyric.ricledger.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elyric.ricledger.data.local.entity.BillStoreEntity

@Dao
interface BillStoreDao {
    @Insert
    suspend fun insertBill(billStoreEntity: BillStoreEntity)
    @Query("SELECT * FROM BillStoreEntity WHERE date = :today")
    suspend fun getBillListByDate(today: Long): List<BillStoreEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBills(bills: List<BillStoreEntity>)
}