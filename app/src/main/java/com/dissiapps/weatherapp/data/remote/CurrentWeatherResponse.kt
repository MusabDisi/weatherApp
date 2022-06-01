package com.dissiapps.weatherapp.data.remote

data class CurrentWeatherResponse(
    val current: CurrentDTO,
    val location: LocationDTO,
    val request: RequestDTO
) : Response()