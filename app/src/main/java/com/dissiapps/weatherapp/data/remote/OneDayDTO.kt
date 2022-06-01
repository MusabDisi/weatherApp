package com.dissiapps.weatherapp.data.remote

data class OneDayDTO(
    val day: String,
    val weatherIconDay: String,
    val weatherIconNight: String,
    val high: Int,
    val low: Int,
    val humidity: Int
)