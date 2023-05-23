package com.example.pitjarustes.data.network.api

import com.example.pitjarustes.data.network.response.AuthResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService {

    @FormUrlEncoded
    @POST("login/loginTest")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
    ): Response<AuthResponse>

}