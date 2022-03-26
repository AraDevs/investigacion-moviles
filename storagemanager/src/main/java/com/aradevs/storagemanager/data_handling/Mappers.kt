package com.aradevs.storagemanager.data_handling

import com.aradevs.domain.User
import com.aradevs.storagemanager.UserEntity

fun UserEntity.toDomain(): User {
    return User(id = this.id, username = this.username)
}