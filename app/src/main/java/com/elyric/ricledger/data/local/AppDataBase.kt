package com.elyric.ricledger.data.local

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import com.elyric.ricledger.data.local.dao.BillStoreDao
import com.elyric.ricledger.data.local.entity.BillStoreEntity
import java.util.concurrent.Executors

@Database(
    entities = [BillStoreEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase(){
    abstract fun BillStoreDao(): BillStoreDao
    companion object {
        private const val DATABASE_NAME = "ric_ledger"
        @Volatile
        private var INSTANCE: AppDataBase? = null
        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE?: synchronized(this){
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    DATABASE_NAME
                ).setJournalMode(RoomDatabase.JournalMode.TRUNCATE).setQueryCallback(
                    { sql, args ->
                        Log.d("RoomSQL", "SQL: $sql  args: $args")
                    },
                    Executors.newSingleThreadExecutor()
                ).build().also { INSTANCE = it }
            }
        }
    }
}