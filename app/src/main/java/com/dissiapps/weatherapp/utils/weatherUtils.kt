package com.dissiapps.weatherapp.utils

import android.content.Context
import com.dissiapps.weatherapp.R

fun getUVRiskOfHarm(uv: Int): String{
    return when{
        uv in 0..2 -> "Low"
        uv in 3..5 -> "Moderate"
        uv in 6..7 -> "High"
        uv in 8..10 -> "Very high"
        uv >= 11 -> "Extreme"
        else -> "unknown"
    }
}

fun getWindDisplayString(context: Context, windSpeed: Int, windDir: String): String{
    val dirArrow = when(windDir){
        "E" -> "→"
        "W" -> "←"
        "N" -> "↑"
        "S" -> "↓"
        "NE" -> "↗"
        "SE" -> "↘"
        "SW" -> "↙"
        "NW" -> "↖"
        else -> ""
    }
    return context.getString(R.string.wind_speed_kmph, windSpeed, dirArrow)
}

fun getHighLowString(context: Context, high: Int, low: Int) =
    context.getString(R.string.high_low_temps, high, low)

fun getValuePercent(context: Context, value: Int) = context.getString(R.string.value_percent_int, value)

fun getValuePercent(context: Context, value: String) = context.getString(R.string.value_percent_string, value)