package com.dissiapps.weatherapp.ui.dailyList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dissiapps.weatherapp.data.Repository
import com.dissiapps.weatherapp.data.local.models.OneDaysData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named
import com.dissiapps.weatherapp.BuildConfig

@HiltViewModel
class DailyListFragmentViewModel @Inject constructor(
    @Named(BuildConfig.DATA_SOURCE) private val repository: Repository
) : ViewModel() {

    companion object{
        val TAG: String = DailyListFragmentViewModel::class.java.simpleName
    }

    private val _data = MutableLiveData<List<OneDaysData>>()
    val data: LiveData<List<OneDaysData>> = _data

    init {
        viewModelScope.launch {
            repository.getOneWeeksWeather(
                onFetchFailed = {onFetchFailed(it)},
                onFetchSuccess = {onFetchSuccess()}
            ).collect {
                _data.value = it.data!! //TODO: check why it expects a null
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