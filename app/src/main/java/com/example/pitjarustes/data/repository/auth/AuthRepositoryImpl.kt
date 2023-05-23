package com.example.pitjarustes.data.repository.auth

import com.example.pitjarustes.data.network.api.AuthService
import com.example.pitjarustes.data.network.interceptor.safeApiCall
import com.example.pitjarustes.data.network.response.AuthResponse
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class AuthRepositoryImpl @Inject constructor(private val api : AuthService) : AuthRepository {
    override suspend fun toLogin(unique: String, password: String): AuthResponse? {
        val request = safeApiCall { api.login( unique, password) }

        return if (!request.isSuccessful || request.failed) null
        else request.body
    }
}