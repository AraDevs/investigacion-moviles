package com.aradevs.storagemanager.datasources.implementations

import com.aradevs.domain.binnacles.Binnacle
import com.aradevs.domain.coroutines.Status
import com.aradevs.domain.general.Company
import com.aradevs.storagemanager.AppDatabase
import com.aradevs.storagemanager.data_handling.fromDomain
import com.aradevs.storagemanager.data_handling.toDomain
import com.aradevs.storagemanager.datasources.DatabaseLocalDataSource

class DatabaseLocalDataSourceImpl(private val db: AppDatabase) : DatabaseLocalDataSource {
    override suspend fun saveBinnacle(binnacle: Binnacle): Status<Unit> {
        return try {
            db.getDatabaseDao().saveBinnacle(binnacle.fromDomain())
            Status.Success(Unit)
        } catch (e: Exception) {
            Status.Error(e)
        }
    }

    override suspend fun getBinnacles(): Status<List<Binnacle>> {
        return try {
            val data = db.getDatabaseDao().getBinnacles().map { it.toDomain() }
            Status.Success(data)
        } catch (e: Exception) {
            Status.Error(e)
        }
    }

    override suspend fun deleteBinnacles(): Status<Unit> {
        return try {
            db.getDatabaseDao().deleteBinnacles()
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