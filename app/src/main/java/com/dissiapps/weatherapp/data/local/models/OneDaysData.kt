package com.dissiapps.weatherapp.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "one_week_data_table")
data class OneDaysData(
    @PrimaryKey
    val dayName: String,
    val dayIconUrl: String,
    val nightIconUrl: String,
    val high: Int,
    val low: Int
)