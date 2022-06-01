package com.dissiapps.weatherapp.data.remote

data class HourlyDataDTO (
    val time: String,
    val weatherIcon: String,
    val temperature: Int,
    val humidity: Int
)