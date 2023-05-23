package com.example.pitjarustes.data.repository.user

import com.example.pitjarustes.data.local.UserDao
import com.example.pitjarustes.data.local.entity.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao: UserDao) : UserRepository {
    override suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    override suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    override suspend fun getUser(): User {
        return userDao.getUserById()
    }

    override suspend fun clearUser() {
        userDao.clearUser()
    }
}