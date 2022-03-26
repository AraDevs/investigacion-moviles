package com.aradevs.domain.general

import java.util.*

data class Company(
    val id: Int,
    val name: String,
    val description: String?,
    val initDate: Date,
)
