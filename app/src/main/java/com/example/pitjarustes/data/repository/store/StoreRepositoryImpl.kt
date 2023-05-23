package com.example.pitjarustes.data.repository.store

import com.example.pitjarustes.data.local.StoreDao
import com.example.pitjarustes.data.local.entity.Store
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(private val storeDao: StoreDao) : StoreRepository {

    override fun getAllStore(): List<Store>? {
        return storeDao.getAllStore()
    }

    override suspend fun insertStore(store: Store) {
        storeDao.insertStore(store)
    }

    override suspend fun deleteStore(store: Store) {
        storeDao.deleteStore(store)
    }

    override suspend fun updateVisited(id: Int, status: String) {
        storeDao.updateStoreVisited(id, status)
    }

    override suspend fun updateStore(store: Store) {
        storeDao.updateStore(store)
    }

    override suspend fun deleteStoreById(store: Int) {
        storeDao.deleteStoreById(store)
    }

    override suspend fun getStoreById(store: Int) : Store {
        return storeDao.getStoreById(store)
    }

}