package com.dissiapps.weatherapp.ui

import android.annotation.SuppressLint
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dissiapps.weatherapp.R
import com.dissiapps.weatherapp.databinding.MapViewFragmentBinding
import com.dissiapps.weatherapp.utils.PermissionsHelper
import com.dissiapps.weatherapp.utils.showToastMessage
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationFragment : Fragment(), OnMapReadyCallback, LocationListener {

    private val viewModel:LocationFragmentViewModel by viewModels()
    private lateinit var binding: MapViewFragmentBinding
    private lateinit var map: GoogleMap
    private lateinit var requestPermissionsLauncher: ActivityResultLauncher<Array<String?>?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissionsLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()){ resultsMap ->
            if (resultsMap[PermissionsHelper.ACCESS_FINE_LOCATION_PERMISSIONS] == false ||
                resultsMap[PermissionsHelper.ACCESS_COARSE_LOCATION_PERMISSIONS] == false){
                binding.apply {
                    locationPermissionText.visibility = View.VISIBLE
                    permissionBackground.visibility = View.VISIBLE
                    permissionBtn.visibility = View.VISIBLE
                }
                showToastMessage(getString(R.string.location_permission_denied))
            }else{
                binding.apply {
                    locationPermissionText.visibility = View.GONE
                    permissionBackground.visibility = View.GONE
                    permissionBtn.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MapViewFragmentBinding.inflate(inflater, container, false)

        val hasPermissions = PermissionsHelper.hasLocationPermissions(this)

        if (hasPermissions)
            hidePermissionsNeededOverlay()
        else
            showPermissionsNeededOverlay()

        binding.permissionBtn.setOnClickListener { requestPermission() }
        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync(this)
        return binding.root
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.uiSettings.isMyLocationButtonEnabled = true

        if (PermissionsHelper.hasCoarseLocationPermission(this) &&
            PermissionsHelper.hasFineLocationPermission(this)){
            map.isMyLocationEnabled = true
        }else{
            requestPermission()
        }

    }

    override fun shouldShowRequestPermissionRationale(permission: String): Boolean {
        PermissionsHelper.goToPermissionScreen(requireContext())
        return super.shouldShowRequestPermissionRationale(permission)
    }

    private fun requestPermission() {
        requestPermissionsLauncher.launch(
            arrayOf(
                PermissionsHelper.ACCESS_FINE_LOCATION_PERMISSIONS,
                PermissionsHelper.ACCESS_COARSE_LOCATION_PERMISSIONS
            )
        )
    }

    private fun showPermissionsNeededOverlay(){
        binding.apply {
            locationPermissionText.visibility = View.VISIBLE
            permissionBackground.visibility = View.VISIBLE
            permissionBtn.visibility = View.VISIBLE
            mapView.visibility = View.GONE
        }
    }

    private fun hidePermissionsNeededOverlay(){
        binding.apply {
            locationPermissionText.visibility = View.GONE
            permissionBackground.visibility = View.GONE
            permissionBtn.visibility = View.GONE
            mapView.visibility = View.VISIBLE
        }
    }

    override fun onLocationChanged(location: Location) {
        map.moveCamera(CameraUpdateFactory.newLatLng(LatLng(location.latitude,location.longitude)))
    }

    override fun onResume() {
        binding.mapView.onResume()
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }


}