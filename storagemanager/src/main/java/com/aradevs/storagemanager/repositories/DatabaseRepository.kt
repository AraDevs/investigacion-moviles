package com.aradevs.storagemanager.repositories

import com.aradevs.domain.logs.Log
import com.aradevs.domain.general.Company
import com.aradevs.storagemanager.datasources.DatabaseLocalDataSource

class DatabaseRepository(private val databaseLocalDataSource: DatabaseLocalDataSource) {
    suspend fun saveBinnacle(log: Log) =
        databaseLocalDataSource.saveBinnacle(log)

    suspend fun getBinnacles() = databaseLocalDataSource.getBinnacles()

    suspend fun deleteBinnacles() = databaseLocalDataSource.deleteBinnacles()

    suspend fun saveCompany(company: Company) = databaseLocalDataSource.saveCompany(company)

    suspend fun getCompany() = databaseLocalDataSource.getCompany()

    suspend fun deleteCompanies() = databaseLocalDataSource.deleteCompanies()
}