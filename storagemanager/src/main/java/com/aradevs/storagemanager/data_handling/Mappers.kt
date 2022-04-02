package com.aradevs.storagemanager.data_handling

import com.aradevs.domain.logs.Log
import com.aradevs.domain.general.Company
import com.aradevs.storagemanager.LogEntity
import com.aradevs.storagemanager.CompanyEntity

fun LogEntity.toDomain(): Log =
    Log(id = this.id,
        description = this.description,
        initDate = this.initDate,
        endDate = this.endDate)

fun CompanyEntity.toDomain(): Company =
    Company(id = this.id,
        name = this.name,
        description = this.description,
        initDate = this.initDate)

fun Log.fromDomain(): LogEntity =
    LogEntity(id = this.id,
        description = this.description,
        initDate = this.initDate,
        endDate = this.endDate)

fun Company.fromDomain(): CompanyEntity =
    CompanyEntity(id = this.id,
        name = this.name,
        description = this.description,
        initDate = this.initDate)