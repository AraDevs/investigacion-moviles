package com.aradevs.storagemanager.modules

import com.aradevs.storagemanager.use_cases.*
import com.aradevs.storagemanager.repositories.DatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCasesModule {
    @Provides
    fun providesSaveLogUseCase(repository: DatabaseRepository): SaveLogUseCase =
        SaveLogUseCase(repository)

    @Provides
    fun providesGetLogsUseCase(repository: DatabaseRepository): GetLogsUseCase =
        GetLogsUseCase(repository)

    @Provides
    fun providesDeleteLogsUseCase(repository: DatabaseRepository): DeleteLogsUseCase =
        DeleteLogsUseCase(repository)

    @Provides
    fun providesSaveCompanyUseCase(repository: DatabaseRepository): SaveCompanyUseCase =
        SaveCompanyUseCase(repository)

    @Provides
    fun providesGetCompanyUseCase(repository: DatabaseRepository): GetCompanyUseCase =
        GetCompanyUseCase(repository)

    @Provides
    fun providesDeleteCompaniesUseCase(repository: DatabaseRepository): DeleteCompaniesUseCase =
        DeleteCompaniesUseCase(repository)

}