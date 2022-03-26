package com.aradevs.storagemanager.modules

import com.aradevs.storagemanager.datasources.UserLocalDataSource
import com.aradevs.storagemanager.repositories.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {
    @Provides
    fun providesUsersRepository(localDataSource: UserLocalDataSource): UsersRepository {
        return UsersRepository(localDataSource)
    }
}