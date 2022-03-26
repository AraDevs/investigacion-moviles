package com.aradevs.storagemanager.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aradevs.storagemanager.UserEntity

@Dao
interface UserDao {
    @Insert
    suspend fun saveUser(userEntity: UserEntity)

    @Query("SELECT * FROM users WHERE username = :username AND password=:password")
    suspend fun getUser(username:String, password: String) : UserEntity?
}