package com.dissiapps.weatherapp.data.remote

data class HourlyDataResponse(
    val hourly: List<HourlyDataDTO>,
    val location: LocationDTO,
    val request: RequestDTO
) : Response()