package com.dissiapps.weatherapp.utils

import android.util.Log
import com.dissiapps.weatherapp.App
import com.dissiapps.weatherapp.R
import com.dissiapps.weatherapp.data.remote.CurrentWeatherResponse
import com.dissiapps.weatherapp.data.remote.HourlyDataResponse
import com.dissiapps.weatherapp.data.remote.OneWeekResponse
import com.dissiapps.weatherapp.data.remote.Response
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import java.lang.StringBuilder

object DummyJSONHelper {

    fun getCurrentWeather() =
        getObjectFromJSONString(R.raw.current_weather, Types.CURRENT) as CurrentWeatherResponse

    fun getWeeklyWeather() =
        getObjectFromJSONString(R.raw.one_week, Types.ONE_WEEK) as OneWeekResponse

    fun getTodayHourlyWeather() =
        getObjectFromJSONString(R.raw.hourly_data, Types.HOURLY) as HourlyDataResponse

    private fun getObjectFromJSONString(resourceFileId: Int, type: Types): Response {
        val gsonBuilder = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

        val jsonString = StringBuilder()

        App.applicationContext().resources.openRawResource(resourceFileId)
            .bufferedReader().forEachLine { jsonString.append(it) }

        val result = when(type){
            Types.CURRENT -> gsonBuilder.fromJson(jsonString.toString(), CurrentWeatherResponse::class.java)
            Types.ONE_WEEK -> gsonBuilder.fromJson(jsonString.toString(), OneWeekResponse::class.java)
            Types.HOURLY -> gsonBuilder.fromJson(jsonString.toString(), HourlyDataResponse::class.java)
        }

        Log.e("TAG", "musab getObjectFromJSONString: $result")
        return result
    }

    enum class Types{
        CURRENT, ONE_WEEK, HOURLY
    }
}