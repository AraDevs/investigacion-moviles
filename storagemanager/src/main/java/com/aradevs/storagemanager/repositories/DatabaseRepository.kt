package com.aradevs.storagemanager.repositories

import com.aradevs.domain.logs.Log
import com.aradevs.domain.general.Company
import com.aradevs.storagemanager.datasources.DatabaseLocalDataSource

class DatabaseRepository(private val databaseLocalDataSource: DatabaseLocalDataSource) {
    suspend fun saveLog(log: Log) =
        databaseLocalDataSource.saveLog(log)

    suspend fun getLogs() = databaseLocalDataSource.getLogs()

    suspend fun deleteLogs() = databaseLocalDataSource.deleteLogs()

    suspend fun saveCompany(company: Company) = databaseLocalDataSource.saveCompany(company)

    suspend fun getCompany() = databaseLocalDataSource.getCompany()

    suspend fun deleteCompanies() = databaseLocalDataSource.deleteCompanies()
}