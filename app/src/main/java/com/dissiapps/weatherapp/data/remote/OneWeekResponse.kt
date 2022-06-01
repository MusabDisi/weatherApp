package com.dissiapps.weatherapp.data.remote

data class OneWeekResponse(
    val oneWeek: List<OneDayDTO>,
    val location: LocationDTO,
    val request: RequestDTO
) : Response()