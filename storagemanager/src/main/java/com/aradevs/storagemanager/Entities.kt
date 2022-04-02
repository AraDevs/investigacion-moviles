package com.aradevs.storagemanager

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "logs")
data class LogEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val description: String,
    @ColumnInfo(name="init_date")
    val initDate: Date,
    @ColumnInfo(name="end_date")
    val endDate: Date
)

@Entity(tableName = "companies")
data class CompanyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val description: String?,
    @ColumnInfo(name="init_date")
    val initDate: Date,
)