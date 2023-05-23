package com.example.pitjarustes.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pitjarustes.data.local.entity.Store
import com.example.pitjarustes.data.local.entity.User

@Database(entities = [Store::class, User::class], version = 2, exportSchema = false)
abstract class StoreDatabase : RoomDatabase() {

    abstract fun getStoreDao(): StoreDao

    abstract fun getUserDao(): UserDao

    companion object {
        private var dbINSTANCE: StoreDatabase? = null

        fun getAppDB(context: Context): StoreDatabase{
            if(dbINSTANCE == null) {
                dbINSTANCE = Room.databaseBuilder(
                    context.applicationContext, StoreDatabase::class.java, "storeTable"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return dbINSTANCE!!
        }
    }
}