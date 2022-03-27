package com.aradevs.storagemanager.repositories

import com.aradevs.domain.binnacles.Binnacle
import com.aradevs.domain.general.Company
import com.aradevs.storagemanager.datasources.DatabaseLocalDataSource

class DatabaseRepository(private val databaseLocalDataSource: DatabaseLocalDataSource) {
    suspend fun saveBinnacle(binnacle: Binnacle) =
        databaseLocalDataSource.saveBinnacle(binnacle)

    suspend fun getBinnacles() = databaseLocalDataSource.getBinnacles()

    suspend fun deleteBinnacles() = databaseLocalDataSource.deleteBinnacles()

    suspend fun saveCompany(company: Company) = databaseLocalDataSource.saveCompany(company)

    suspend fun getCompany() = databaseLocalDataSource.getCompany()

    suspend fun deleteCompanies() = databaseLocalDataSource.deleteCompanies()
}