package com.dissiapps.weatherapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dissiapps.weatherapp.data.local.models.CurrentWeather
import com.dissiapps.weatherapp.data.local.models.OneDaysData
import com.dissiapps.weatherapp.data.local.models.ExtraInfo
import com.dissiapps.weatherapp.data.local.models.OneHourData

@Database(
    entities = [CurrentWeather::class, OneDaysData::class, OneHourData::class, ExtraInfo::class],
    version = 1,
    exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun getCurrentWeatherDao(): CurrentWeatherDao
    abstract fun getDailyDataDao(): OneWeekDataDao
    abstract fun getHourlyDataDao(): HourlyDataDao
    abstract fun getExtraInfoDao(): ExtraInfoDao
}