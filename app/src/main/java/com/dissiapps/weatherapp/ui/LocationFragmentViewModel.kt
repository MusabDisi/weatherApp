package com.dissiapps.weatherapp.ui

import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.dissiapps.weatherapp.utils.PermissionsHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationFragmentViewModel @Inject constructor(): ViewModel()