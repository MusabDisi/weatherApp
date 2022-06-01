package com.dissiapps.weatherapp.utils

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject


@InstallIn(SingletonComponent::class)
@Module
class PermissionsHelper @Inject constructor() {

    companion object{
        const val ACCESS_FINE_LOCATION_PERMISSIONS = Manifest.permission.ACCESS_FINE_LOCATION
        const val ACCESS_COARSE_LOCATION_PERMISSIONS = Manifest.permission.ACCESS_COARSE_LOCATION

        fun hasLocationPermissions(fragment: Fragment) =
            hasFineLocationPermission(fragment) && hasCoarseLocationPermission(fragment)

        fun hasFineLocationPermission(fragment: Fragment): Boolean{
            return ActivityCompat.checkSelfPermission(fragment.requireContext()
                , ACCESS_COARSE_LOCATION_PERMISSIONS) == PackageManager.PERMISSION_GRANTED
        }

        fun hasCoarseLocationPermission(fragment: Fragment): Boolean{
            return ActivityCompat.checkSelfPermission(fragment.requireContext()
                , ACCESS_FINE_LOCATION_PERMISSIONS) == PackageManager.PERMISSION_GRANTED
        }

        fun goToPermissionScreen(context: Context){
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri: Uri = Uri.fromParts("package", context.packageName, null)
            intent.data = uri
            context.startActivity(intent)
        }
    }
}