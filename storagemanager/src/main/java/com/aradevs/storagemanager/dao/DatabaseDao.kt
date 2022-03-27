package com.aradevs.storagemanager.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aradevs.storagemanager.BinnacleEntity
import com.aradevs.storagemanager.CompanyEntity

@Dao
interface DatabaseDao {
    @Insert
    suspend fun saveBinnacle(binnacleEntity: BinnacleEntity)

    @Query("SELECT * FROM binnacles")
    suspend fun getBinnacles(): List<BinnacleEntity>

    @Query("DELETE FROM binnacles where id = :id")
    suspend fun deleteBinnacle(id: Int)

    @Insert
    suspend fun saveCompany(companyEntity: CompanyEntity)

    @Query("SELECT * FROM companies LIMIT 1")
    suspend fun getCompany(): CompanyEntity?

    @Query("DELETE FROM companies")
    suspend fun deleteCompanies()
}