package com.dissiapps.weatherapp.utils

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.dissiapps.weatherapp.R
import com.dissiapps.weatherapp.data.local.models.CurrentWeather
import com.dissiapps.weatherapp.data.local.models.ExtraInfo
import com.dissiapps.weatherapp.data.local.models.OneDaysData
import com.dissiapps.weatherapp.data.local.models.OneHourData
import com.dissiapps.weatherapp.data.remote.CurrentWeatherResponse
import com.dissiapps.weatherapp.data.remote.HourlyDataResponse
import com.dissiapps.weatherapp.data.remote.OneWeekResponse

fun CurrentWeatherResponse.extractCurrentWeatherInfo(): CurrentWeather {
    val current = this.current
    Log.e("TAG", "zu extractCurrentWeatherInfo: $current", )
    return CurrentWeather(
        cloudcover = current.cloudcover,
        feelslike = current.feelslike,
        isDay = current.isDay,
        observationTime = current.observationTime,
        precip = current.precip,
        pressure = current.pressure,
        temperature = current.temperature,
        visibility = current.visibility,
        weatherDescriptions = current.weatherDescriptions[0],
        weatherIcon = current.weatherIcon,
        cityName = this.location.name
    )
}

fun OneWeekResponse.convertToPOJO(): List<OneDaysData> {
    return this.oneWeek.map {
        Log.e("TAG", "convertToPOJO: $it", )
        OneDaysData(
            dayName = it.day,
            dayIconUrl = it.weatherIconDay,
            nightIconUrl = it.weatherIconNight,
            high = it.high,
            low = it.low
        )
    }
}

fun HourlyDataResponse.convertToPOJO(): List<OneHourData> {
    return this.hourly.map {
        OneHourData(
            exactHour = it.time,
            iconUrl = it.weatherIcon,
            temp = it.temperature.toString()
        )
    }
}

fun CurrentWeatherResponse.extractExtraInfo(): List<ExtraInfo> {
    val current = this.current
    return listOf(
        ExtraInfo(ExtraInfoItemsIds.UV_ID, R.drawable.ic_uv, "UV index", current.uvIndex.toString()),
        ExtraInfo(ExtraInfoItemsIds.SUNRISE_ID, R.drawable.ic_sunrise, "Sunrise", current.sunrise),
        ExtraInfo(ExtraInfoItemsIds.SUNSET_ID, R.drawable.ic_sunset, "Sunset", current.sunset),
        ExtraInfo(ExtraInfoItemsIds.WIND_ID, R.drawable.ic_wind, "Wind", current.windSpeed.toString()),
        ExtraInfo(ExtraInfoItemsIds.HUMIDITY_ID, R.drawable.ic_humidity, "Humidity", current.humidity.toString()),
    )
}

fun Fragment.showToastMessage(message: String, length: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this.context, message, length).show()
}