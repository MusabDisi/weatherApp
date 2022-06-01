package com.dissiapps.weatherapp.data.remote

import com.google.gson.annotations.SerializedName

data class CurrentDTO(
    val cloudcover: Int,
    val feelslike: Int,
    val humidity: Int,
    val isDay: String,
    val observationTime: String,
    val precip: Int,
    val pressure: Int,
    val temperature: Int,
    val uvIndex: Int,
    val visibility: Int,
    val weatherDescriptions: List<String>,
    val weatherIcon: String,
    val windDegree: Int,
    val windDir: String,
    val windSpeed: Int,
    val sunset: String,
    val sunrise: String,
    val high: Int,
    val low: Int
)