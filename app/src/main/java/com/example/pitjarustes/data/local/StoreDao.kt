package com.example.pitjarustes.data.local

import androidx.room.*
import com.example.pitjarustes.data.local.entity.Store

@Dao
interface StoreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStore(store : Store)

    @Delete
    suspend fun deleteStore(store: Store)

    @Update
    suspend fun updateStore(store: Store)

    @Query("UPDATE storeTable SET store_status = :newValue WHERE store_id = :id")
    suspend fun updateStoreVisited(id: Int, newValue: String)

    @Query("select * from storeTable order by store_id ASC")
    fun getAllStore(): List<Store>

    @Query("select * FROM storeTable WHERE store_id = :id")
    suspend fun getStoreById(id: Int) : Store

    @Query("DELETE FROM storeTable WHERE store_id = :id")
    suspend fun deleteStoreById(id: Int)
}