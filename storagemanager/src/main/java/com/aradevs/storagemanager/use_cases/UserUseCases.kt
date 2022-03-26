package com.aradevs.storagemanager.use_cases

import com.aradevs.storagemanager.repositories.UsersRepository

class GetUserUseCase(private val repository: UsersRepository) {
    suspend operator fun invoke(username: String, password: String) =
        repository.getUser(username, password)
}

class SaveUserUseCase(private val repository: UsersRepository) {
    suspend operator fun invoke(username: String, password: String) =
        repository.saveUser(username, password)
}
