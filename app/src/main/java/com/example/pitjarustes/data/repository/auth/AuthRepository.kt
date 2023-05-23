package com.example.pitjarustes.data.repository.auth

import com.example.pitjarustes.data.network.response.AuthResponse

interface AuthRepository {
    suspend fun toLogin(
        unique: String, password: String
    ): AuthResponse?
}