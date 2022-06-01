package com.dissiapps.weatherapp.data.remote

data class LocationDTO(
    val country: String,
    val localtime: String,
    val name: String,
    val region: String,
    val timezoneId: String,
    val utcOffset: String
)