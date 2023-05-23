package com.example.pitjarustes.data.repository.user

import com.example.pitjarustes.data.local.entity.User

interface UserRepository {
    suspend fun addUser(user: User)

    suspend fun deleteUser(user: User)

    suspend fun getUser() : User

    suspend fun clearUser()
}