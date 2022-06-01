package com.dissiapps.weatherapp.data.remote

import retrofit2.http.GET

interface WeatherApi {

    companion object{
        const val BASE_URL = "https://1009f137-a24c-4e36-b1e3-c9aa941d305d.mock.pstmn.io"
    }

    @GET("weather/current")
    suspend fun getCurrentWeather(): CurrentWeatherResponse

    @GET("weather/weeklyData")
    suspend fun getWeeklyWeather(): OneWeekResponse

    @GET("weather/dailyData")
    suspend fun getTodayHourlyWeather(): HourlyDataResponse
}