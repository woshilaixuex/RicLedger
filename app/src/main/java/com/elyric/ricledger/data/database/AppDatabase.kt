package com.elyric.ricledger.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.elyric.ricledger.data.local.dao.BillStoreDao
import com.elyric.ricledger.data.local.entity.BillStoreEntity

@Database(
    entities = [BillStoreEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun BillStoreDao(): BillStoreDao
    companion object {
        private const val DATABASE_NAME = "app_bill_store"
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE?: synchronized(this){
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build().also { INSTANCE = it }
            }
        }
    }
}