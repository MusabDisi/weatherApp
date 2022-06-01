package com.dissiapps.weatherapp.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hourly_data_table")
data class OneHourData(
    @PrimaryKey
    val exactHour: String,
    val iconUrl: String,
    val temp: String
)