package com.aradevs.storagemanager.datasources

import com.aradevs.domain.User
import com.aradevs.domain.binnacles.Binnacle
import com.aradevs.domain.coroutines.Status
import com.aradevs.domain.general.Company

interface DatabaseLocalDataSource {
    suspend fun saveBinnacle(binnacle: Binnacle): Status<Unit>
    suspend fun getBinnacles(): Status<List<Binnacle>>
    suspend fun deleteBinnacle(id: Int): Status<Unit>
    suspend fun saveCompany(company: Company): Status<Unit>
    suspend fun getCompany(): Status<Company?>
    suspend fun deleteCompanies(): Status<Unit>
}