package com.aradevs.storagemanager.use_cases

import com.aradevs.domain.logs.Log
import com.aradevs.domain.general.Company
import com.aradevs.storagemanager.repositories.DatabaseRepository

class SaveLogUseCase(private val repository: DatabaseRepository) {
    suspend operator fun invoke(log: Log) =
        repository.saveLog(log)
}

class GetLogsUseCase(private val repository: DatabaseRepository) {
    suspend operator fun invoke() = repository.getLogs()
}

class DeleteLogsUseCase(private val repository: DatabaseRepository) {
    suspend operator fun invoke() = repository.deleteLogs()
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

