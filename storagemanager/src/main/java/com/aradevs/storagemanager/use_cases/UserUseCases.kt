package com.aradevs.storagemanager.use_cases

import com.aradevs.domain.binnacles.Binnacle
import com.aradevs.domain.general.Company
import com.aradevs.storagemanager.repositories.DatabaseRepository

class SaveBinnacleUseCase(private val repository: DatabaseRepository) {
    suspend operator fun invoke(binnacle: Binnacle) =
        repository.saveBinnacle(binnacle)
}

class GetBinnaclesUseCase(private val repository: DatabaseRepository) {
    suspend operator fun invoke() = repository.getBinnacles()
}

class DeleteBinnaclesUseCase(private val repository: DatabaseRepository) {
    suspend operator fun invoke() = repository.deleteBinnacles()
}

class SaveCompanyUseCase(private val repository: DatabaseRepository) {
    suspend operator fun invoke(company: Company) = repository.saveCompany(company)
}

class GetCompanyUseCase(private val repository: DatabaseRepository) {
    suspend operator fun invoke() = repository.getCompany()
}

class DeleteCompaniesUseCase(private val repository: DatabaseRepository) {
    suspend operator fun invoke() = repository.deleteCompanies()
}

