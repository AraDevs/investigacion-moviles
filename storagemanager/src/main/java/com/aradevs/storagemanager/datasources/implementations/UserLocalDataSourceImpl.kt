package com.aradevs.storagemanager.datasources.implementations

import com.aradevs.domain.Status
import com.aradevs.domain.User
import com.aradevs.storagemanager.AppDatabase
import com.aradevs.storagemanager.UserEntity
import com.aradevs.storagemanager.data_handling.toDomain
import com.aradevs.storagemanager.datasources.UserLocalDataSource
import java.lang.Exception

class UserLocalDataSourceImpl(private val db: AppDatabase) : UserLocalDataSource {
    override suspend fun getUser(username: String, password: String): Status<User?> {
        return try{
            val user = db.getUsersDao().getUser(username, password)?.toDomain()
            Status.Success(user)
        }catch (e: Exception){
            Status.Error(e)
        }
    }

    override suspend fun saveUser(username: String, password: String): Status<Unit> {
        return try {
            val user = UserEntity(id = 0,username = username, password = password)
            db.getUsersDao().saveUser(user)
            Status.Success(Unit)
        }catch (e: Exception){
            Status.Error(e)
        }
    }
}