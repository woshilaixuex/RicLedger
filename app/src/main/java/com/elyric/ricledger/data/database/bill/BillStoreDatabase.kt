package com.elyric.ricledger.data.database.bill

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BillStore::class], version = 1, exportSchema = false)
abstract class BillStoreDatabase: RoomDatabase() {
    abstract fun BillStoreDao(): BillStoreDao
    companion object {
        private const val DATABASE_NAME = "bill_store"
        @Volatile
        private var INSTANCE: BillStoreDatabase? = null
        fun getDatabase(context: Context): BillStoreDatabase {
            return INSTANCE?: synchronized(this){
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    BillStoreDatabase::class.java,
                    DATABASE_NAME
                ).build().also { INSTANCE = it }
            }
        }
    }
}