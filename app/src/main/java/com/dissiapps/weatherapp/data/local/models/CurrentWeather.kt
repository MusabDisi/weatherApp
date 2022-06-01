package com.dissiapps.weatherapp.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_weather_table")
data class CurrentWeather (
    @PrimaryKey
    val timeStamp: Long = System.currentTimeMillis(),
    val cityName: String,
    val cloudcover: Int,
    val feelslike: Int,
    val isDay: String,
    val observationTime: String,
    val precip: Int,
    val pressure: Int,
    val temperature: Int,
    val visibility: Int,
    val weatherDescriptions: String,
    val weatherIcon: String,
    )
