package com.aradevs.storagemanager.modules

import com.aradevs.storagemanager.repositories.UsersRepository
import com.aradevs.storagemanager.use_cases.GetUserUseCase
import com.aradevs.storagemanager.use_cases.SaveUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCasesModule {
    @Provides
    fun providesGetUserUseCase(repository: UsersRepository) : GetUserUseCase {
        return  GetUserUseCase(repository)
    }

    @Provides
    fun providesSaveUserUseCase(repository: UsersRepository) : SaveUserUseCase{
        return SaveUserUseCase(repository)
    }
}