package com.aradevs.storagemanager.datasources

import com.aradevs.domain.Status
import com.aradevs.domain.User

interface UserLocalDataSource {
    suspend fun getUser(username:String, password: String) : Status<User?>
    suspend fun saveUser(username: String, password: String): Status<Unit>
}