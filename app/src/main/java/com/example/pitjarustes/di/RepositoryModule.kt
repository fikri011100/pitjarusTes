package com.example.pitjarustes.di

import com.example.pitjarustes.data.network.api.AuthService
import com.example.pitjarustes.data.repository.auth.AuthRepository
import com.example.pitjarustes.data.repository.auth.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAuthRepo(api: AuthService): AuthRepository = AuthRepositoryImpl(api)

}