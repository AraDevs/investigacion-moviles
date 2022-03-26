package com.aradevs.storagemanager.data_handling

import com.aradevs.domain.binnacles.Binnacle
import com.aradevs.domain.general.Company
import com.aradevs.storagemanager.BinnacleEntity
import com.aradevs.storagemanager.CompanyEntity

fun BinnacleEntity.toDomain(): Binnacle =
    Binnacle(id = this.id,
        description = this.description,
        initDate = this.initDate,
        endDate = this.endDate)

fun CompanyEntity.toDomain(): Company =
    Company(id = this.id,
        name = this.name,
        description = this.description,
        initDate = this.initDate)

fun Binnacle.fromDomain(): BinnacleEntity =
    BinnacleEntity(id = this.id,
        description = this.description,
        initDate = this.initDate,
        endDate = this.endDate)

fun Company.fromDomain(): CompanyEntity =
    CompanyEntity(id = this.id,
        name = this.name,
        description = this.description,
        initDate = this.initDate)