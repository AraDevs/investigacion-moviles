package com.aradevs.domain.general

import java.util.*

/**
 * A [Company] object
 * @property id represents the company identifier
 * @property name represents the company name
 * @property description represents a brief description of the company
 * @property initDate represents the starting point of the social work
 */
data class Company(
    val id: Int,
    val name: String,
    val description: String?,
    val initDate: Date,
)
