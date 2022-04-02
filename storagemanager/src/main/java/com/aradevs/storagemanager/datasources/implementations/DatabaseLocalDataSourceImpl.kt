package com.aradevs.storagemanager.datasources.implementations

import com.aradevs.domain.logs.Log
import com.aradevs.domain.coroutines.Status
import com.aradevs.domain.general.Company
import com.aradevs.storagemanager.AppDatabase
import com.aradevs.storagemanager.data_handling.fromDomain
import com.aradevs.storagemanager.data_handling.toDomain
import com.aradevs.storagemanager.datasources.DatabaseLocalDataSource

class DatabaseLocalDataSourceImpl(private val db: AppDatabase) : DatabaseLocalDataSource {
    override suspend fun saveLog(log: Log): Status<Unit> {
        return try {
            db.getDatabaseDao().saveLog(log.fromDomain())
            Status.Success(Unit)
        } catch (e: Exception) {
            Status.Error(e)
        }
    }

    override suspend fun getLogs(): Status<List<Log>> {
        return try {
            val data = db.getDatabaseDao().getLogs().map { it.toDomain() }
            Status.Success(data)
        } catch (e: Exception) {
            Status.Error(e)
        }
    }

    override suspend fun deleteLogs(): Status<Unit> {
        return try {
            db.getDatabaseDao().deleteLogs()
            Status.Success(Unit)
        } catch (e: Exception) {
            Status.Error(e)
        }
    }

    override suspend fun saveCompany(company: Company): Status<Unit> {
        return try {
            db.getDatabaseDao().saveCompany(company.fromDomain())
            Status.Success(Unit)
        } catch (e: Exception) {
            Status.Error(e)
        }
    }

    override suspend fun getCompany(): Status<Company?> {
        return try {
            val company = db.getDatabaseDao().getCompany()?.toDomain()
            Status.Success(company)
        } catch (e: Exception) {
            Status.Error(e)
        }
    }

    override suspend fun deleteCompanies(): Status<Unit> {
        return try {
            db.getDatabaseDao().deleteCompanies()
            Status.Success(Unit)
        } catch (e: Exception) {
            Status.Error(e)
        }
    }
}