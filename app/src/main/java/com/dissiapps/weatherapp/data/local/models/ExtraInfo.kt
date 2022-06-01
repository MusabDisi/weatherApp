package com.dissiapps.weatherapp.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "extra_info_table")
data class ExtraInfo(
    @PrimaryKey
    val id: String,
    val iconDrawable: Int,
    val infoTitle: String,
    val infoValue: String
)