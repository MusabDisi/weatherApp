package com.dissiapps.weatherapp.data.dummy

import android.util.Log
import androidx.room.withTransaction
import com.dissiapps.weatherapp.data.Repository
import com.dissiapps.weatherapp.data.local.models.CurrentWeather
import com.dissiapps.weatherapp.data.local.models.OneDaysData
import com.dissiapps.weatherapp.data.local.WeatherDatabase
import com.dissiapps.weatherapp.data.local.models.ExtraInfo
import com.dissiapps.weatherapp.data.local.models.OneHourData
import com.dissiapps.weatherapp.data.remote.CurrentWeatherResponse
import com.dissiapps.weatherapp.networkBoundResources.Resource
import com.dissiapps.weatherapp.networkBoundResources.networkBoundResource
import com.dissiapps.weatherapp.utils.DummyJSONHelper
import com.dissiapps.weatherapp.utils.convertToPOJO
import com.dissiapps.weatherapp.utils.extractCurrentWeatherInfo
import com.dissiapps.weatherapp.utils.extractExtraInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DummyRepository @Inject constructor(
    private val weatherDatabase: WeatherDatabase,
): Repository {

    companion object{
        private val TAG = DummyRepository::class.java.simpleName
    }

    private val currentWeatherDao = weatherDatabase.getCurrentWeatherDao()
    private val dailyDataDao = weatherDatabase.getDailyDataDao()
    private val hourlyDataDao = weatherDatabase.getHourlyDataDao()
    private val extraInfoDao = weatherDatabase.getExtraInfoDao()

    override fun getCurrentWeather(
        onFetchSuccess: () -> Unit,
        onFetchFailed: (Throwable) -> Unit)
    : Flow<Resource<CurrentWeather>> {
        Log.i(TAG, "getCurrentWeather() => called")
        return networkBoundResource(
            query = {currentWeatherDao.getCurrentWeatherData()},
            fetch = {
                val response = DummyJSONHelper.getCurrentWeather()
                response
            },
            saveFetchResult = {
                splitAndSaveCurrentWeatherData(it)
            },
            onFetchSuccess = {onFetchSuccess()},
            onFetchFailed = {onFetchFailed(it)}
        )
    }


    override fun getOneWeeksWeather(
        onFetchSuccess: () -> Unit,
        onFetchFailed: (Throwable) -> Unit)
            : Flow<Resource<List<OneDaysData>>> {
        Log.i(TAG, "getOneWeeksWeather() => called")
        return networkBoundResource(
            query = {dailyDataDao.getOneWeekData()},
            fetch = {
                val response = DummyJSONHelper.getWeeklyWeather()
                response
            },
            saveFetchResult = {
                val data = it.convertToPOJO()
                weatherDatabase.withTransaction {
                    dailyDataDao.nukeTheOneWeekDataTable()
                    dailyDataDao.insertOneWeekData(data)
                }
            },
            onFetchSuccess = {onFetchSuccess()},
            onFetchFailed = {onFetchFailed(it)}
        )
    }


    override fun getHourlyData(
        onFetchSuccess: () -> Unit,
        onFetchFailed: (Throwable) -> Unit)
            : Flow<Resource<List<OneHourData>>> {
        Log.i(TAG, "getHourlyData() => called")
        return networkBoundResource(
            query = {hourlyDataDao.getHourlyData()},
            fetch = {
                val response = DummyJSONHelper.getTodayHourlyWeather()
                response
            },
            saveFetchResult = {
                val data = it.convertToPOJO()
                weatherDatabase.withTransaction {
                    hourlyDataDao.nukeTheHourlyDataTable()
                    hourlyDataDao.insertHourlyDataList(data)
                }
            },
            onFetchSuccess = {onFetchSuccess()},
            onFetchFailed = {onFetchFailed(it)}
        )
    }


    override fun getExtraInformation(
        onFetchSuccess: () -> Unit,
        onFetchFailed: (Throwable) -> Unit)
            : Flow<Resource<List<ExtraInfo>>> {
        Log.i(TAG, "getExtraInformation() => called")
        return networkBoundResource(
            query = {extraInfoDao.getExtraInfoList()},
            fetch = {
                val response = DummyJSONHelper.getCurrentWeather()
                response
            },
            saveFetchResult = {
                splitAndSaveCurrentWeatherData(it)
            },
            onFetchSuccess = {onFetchSuccess()},
            onFetchFailed = {onFetchFailed(it)}
        )
    }


    override suspend fun splitAndSaveCurrentWeatherData(currentWeatherResponse: CurrentWeatherResponse){
        val extraInfo = currentWeatherResponse.extractExtraInfo()
        val currentWeather = currentWeatherResponse.extractCurrentWeatherInfo()
        weatherDatabase.withTransaction {
            currentWeatherDao.nukeTheCurrentWeatherTable()
            currentWeatherDao.insertCurrentWeather(currentWeather)
            extraInfoDao.nukeTheExtraInfoTable()
            extraInfoDao.insertExtraInfoList(extraInfo)
        }
    }



}