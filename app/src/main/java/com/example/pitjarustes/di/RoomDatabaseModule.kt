package com.example.pitjarustes.di

import android.app.Application
import com.example.pitjarustes.data.local.StoreDao
import com.example.pitjarustes.data.local.StoreDatabase
import com.example.pitjarustes.data.local.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDatabaseModule {

    @Singleton
    @Provides
    fun getAppDB(context: Application): StoreDatabase {
        return StoreDatabase.getAppDB(context)
    }

    @Singleton
    @Provides
    fun getDao(appDB: StoreDatabase): StoreDao {
        return appDB.getStoreDao()
    }

    @Singleton
    @Provides
    fun getUserDao(appDB: StoreDatabase): UserDao {
        return appDB.getUserDao()
    }
}