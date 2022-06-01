package com.dissiapps.weatherapp.data

import com.dissiapps.weatherapp.data.local.models.CurrentWeather
import com.dissiapps.weatherapp.data.local.models.ExtraInfo
import com.dissiapps.weatherapp.data.local.models.OneDaysData
import com.dissiapps.weatherapp.data.local.models.OneHourData
import com.dissiapps.weatherapp.data.remote.CurrentWeatherResponse
import com.dissiapps.weatherapp.networkBoundResources.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getCurrentWeather(
        onFetchSuccess: () -> Unit = {},
        onFetchFailed: (Throwable) -> Unit = {}): Flow<Resource<CurrentWeather>>


    fun getOneWeeksWeather(
        onFetchSuccess: () -> Unit = {},
        onFetchFailed: (Throwable) -> Unit = {}): Flow<Resource<List<OneDaysData>>>


    fun getHourlyData(
        onFetchSuccess: () -> Unit = {},
        onFetchFailed: (Throwable) -> Unit = {}): Flow<Resource<List<OneHourData>>>


    fun getExtraInformation(
        onFetchSuccess: () -> Unit = {},
        onFetchFailed: (Throwable) -> Unit = {}): Flow<Resource<List<ExtraInfo>>>


    suspend fun splitAndSaveCurrentWeatherData(currentWeatherResponse: CurrentWeatherResponse)


}