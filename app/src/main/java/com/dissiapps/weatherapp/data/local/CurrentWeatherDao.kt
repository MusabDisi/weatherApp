package com.dissiapps.weatherapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dissiapps.weatherapp.data.local.models.CurrentWeather
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentWeather(currentWeather: CurrentWeather)

    @Query("SELECT * FROM current_weather_table")
    fun getCurrentWeatherData() : Flow<CurrentWeather>

    @Query("DELETE FROM current_weather_table")
    suspend fun nukeTheCurrentWeatherTable()

}