package com.aradevs.storagemanager.modules

import android.content.Context
import com.aradevs.storagemanager.AppDatabase
import com.aradevs.storagemanager.datasources.implementations.UserLocalDataSourceImpl
import com.aradevs.storagemanager.datasources.UserLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class DatabaseModule {
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    fun providesLocalDataSourceImpl(db: AppDatabase): UserLocalDataSource {
        return UserLocalDataSourceImpl(db)
    }
}