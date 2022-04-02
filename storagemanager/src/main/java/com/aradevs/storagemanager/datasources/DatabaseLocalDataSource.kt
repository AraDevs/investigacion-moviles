package com.aradevs.storagemanager.datasources

import com.aradevs.domain.logs.Log
import com.aradevs.domain.coroutines.Status
import com.aradevs.domain.general.Company

interface DatabaseLocalDataSource {
    suspend fun saveBinnacle(log: Log): Status<Unit>
    suspend fun getBinnacles(): Status<List<Log>>
    suspend fun deleteBinnacles(): Status<Unit>
    suspend fun saveCompany(company: Company): Status<Unit>
    suspend fun getCompany(): Status<Company?>
    suspend fun deleteCompanies(): Status<Unit>
}