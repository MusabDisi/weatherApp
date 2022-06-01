package com.dissiapps.weatherapp.ui.extraInformationList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dissiapps.weatherapp.data.Repository
import com.dissiapps.weatherapp.data.local.models.ExtraInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named
import com.dissiapps.weatherapp.BuildConfig

@HiltViewModel
class ExtraInformationViewModel @Inject constructor(
    @Named(BuildConfig.DATA_SOURCE) private val repository: Repository
) : ViewModel() {

    companion object{
        val TAG: String = ExtraInformationViewModel::class.java.simpleName
    }

    private val _data = MutableLiveData<List<ExtraInfo>>()
    val data: LiveData<List<ExtraInfo>> = _data

    init {
        viewModelScope.launch {
            repository.getExtraInformation(
                onFetchFailed = {onFetchFailed(it)},
                onFetchSuccess = {onFetchSuccess()}
            ).collect {
                _data.value = it.data ?: return@collect
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