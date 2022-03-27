package com.aradevs.domain.binnacles

import java.util.*

/**
 * A [Binnacle] object
 * @property id represents the binnacle identifier
 * @property description represents the description of the work done in the specified time period
 * @property initDate represents the starting point of the binnacle record
 * @property endDate represents the end point of the binnacle record
 */
data class Binnacle(
    val id: Int,
    val description: String,
    val initDate: Date,
    val endDate: Date
)
