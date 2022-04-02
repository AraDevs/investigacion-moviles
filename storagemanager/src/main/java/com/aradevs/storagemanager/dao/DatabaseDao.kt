package com.aradevs.storagemanager.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aradevs.storagemanager.LogEntity
import com.aradevs.storagemanager.CompanyEntity

@Dao
interface DatabaseDao {
    @Insert
    suspend fun saveBinnacle(logEntity: LogEntity)

    @Query("SELECT * FROM logs")
    suspend fun getBinnacles(): List<LogEntity>

    @Query("DELETE FROM logs")
    suspend fun deleteBinnacles()

    @Insert
    suspend fun saveCompany(companyEntity: CompanyEntity)

    @Query("SELECT * FROM companies LIMIT 1")
    suspend fun getCompany(): CompanyEntity?

    @Query("DELETE FROM companies")
    suspend fun deleteCompanies()
}