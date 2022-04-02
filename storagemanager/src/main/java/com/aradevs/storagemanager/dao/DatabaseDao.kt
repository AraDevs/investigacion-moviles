package com.aradevs.storagemanager.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aradevs.storagemanager.LogEntity
import com.aradevs.storagemanager.CompanyEntity

@Dao
interface DatabaseDao {
    @Insert
    suspend fun saveLog(logEntity: LogEntity)

    @Query("SELECT * FROM logs")
    suspend fun getLogs(): List<LogEntity>

    @Query("DELETE FROM logs")
    suspend fun deleteLogs()

    @Insert
    suspend fun saveCompany(companyEntity: CompanyEntity)

    @Query("SELECT * FROM companies LIMIT 1")
    suspend fun getCompany(): CompanyEntity?

    @Query("DELETE FROM companies")
    suspend fun deleteCompanies()
}