package com.example.pitjarustes.data.local

import androidx.room.*
import com.example.pitjarustes.data.local.entity.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user : User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("select * FROM userTable")
    suspend fun getUserById() : User

    @Query("DELETE FROM userTable")
    suspend fun clearUser()
}