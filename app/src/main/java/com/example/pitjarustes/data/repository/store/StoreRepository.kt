package com.example.pitjarustes.data.repository.store

import com.example.pitjarustes.data.local.entity.Store

interface StoreRepository {

    fun getAllStore(): List<Store>?

    suspend fun insertStore(store: Store)

    suspend fun deleteStore(store: Store)

    suspend fun updateVisited(id: Int, status: String)

    suspend fun updateStore(store: Store)

    suspend fun deleteStoreById(store: Int)

    suspend fun getStoreById(store: Int) : Store
}