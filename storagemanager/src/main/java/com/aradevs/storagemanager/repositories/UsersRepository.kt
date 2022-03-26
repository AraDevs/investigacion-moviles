package com.aradevs.storagemanager.repositories

import com.aradevs.storagemanager.datasources.UserLocalDataSource

class UsersRepository(private val userLocalDataSource: UserLocalDataSource) {
    suspend fun getUser(username: String, password: String) =
        userLocalDataSource.getUser(username, password)

    suspend fun saveUser(username: String, password: String) =
        userLocalDataSource.saveUser(username, password)
}