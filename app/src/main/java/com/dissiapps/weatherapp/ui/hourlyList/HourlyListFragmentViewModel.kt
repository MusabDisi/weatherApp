package com.dissiapps.weatherapp.ui.hourlyList

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dissiapps.weatherapp.data.Repository
import com.dissiapps.weatherapp.data.local.models.CurrentWeather
import com.dissiapps.weatherapp.data.local.models.OneHourData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named
import com.dissiapps.weatherapp.BuildConfig

@HiltViewModel
class HourlyListFragmentViewModel @Inject constructor(
    @Named(BuildConfig.DATA_SOURCE) private val repository: Repository
) : ViewModel() {

    companion object{
        val TAG:String = HourlyListFragmentViewModel::class.java.simpleName
    }

    private val _currentWeatherData = MutableLiveData<CurrentWeather>()
    val currentWeatherData: LiveData<CurrentWeather> = _currentWeatherData

    private val _oneDaysData = MutableLiveData<List<OneHourData>>()
    val oneDaysData: LiveData<List<OneHourData>> = _oneDaysData

    init {
        viewModelScope.launch {
            repository.getCurrentWeather(
                onFetchFailed = {onFetchFailed(it)},
                onFetchSuccess = {onFetchSuccess()}
            ).collect {
                _currentWeatherData.value = it.data ?: return@collect
            }
        }
        viewModelScope.launch {
            repository.getHourlyData().collect {
                _oneDaysData.value = it.data!!
            }
        }
    }

    private fun onFetchFailed(tr: Throwable){
        Log.e(TAG, "Fetching failed!", tr)
    }

    private fun onFetchSuccess(){
        Log.d(TAG, "Fetching succeeded.")
    }
}
