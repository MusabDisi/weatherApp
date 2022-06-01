package com.dissiapps.weatherapp.ui.hourlyList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.dissiapps.weatherapp.R
import com.dissiapps.weatherapp.data.local.models.CurrentWeather
import com.dissiapps.weatherapp.data.local.models.OneHourData
import com.dissiapps.weatherapp.databinding.HourlyListFragmentBinding
import com.dissiapps.weatherapp.databinding.MainCardBinding
import com.dissiapps.weatherapp.ui.hourlyList.hourlyListRecycler.HourlyListAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HourlyListFragment : Fragment() {

    companion object{
        private val TAG: String = HourlyListFragment::class.java.simpleName
    }

    private val viewModel: HourlyListFragmentViewModel by viewModels()
    private lateinit var hourlyListAdapter: HourlyListAdapter
    private lateinit var hourlyListFragmentBinding: HourlyListFragmentBinding
    private lateinit var mainCardBinding: MainCardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        hourlyListAdapter = HourlyListAdapter()
        hourlyListFragmentBinding = HourlyListFragmentBinding.inflate(inflater, container, false)
        mainCardBinding = MainCardBinding.bind(hourlyListFragmentBinding.root)
        mainCardBinding.recycler.adapter = hourlyListAdapter
        return hourlyListFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentWeatherObserver = Observer<CurrentWeather> {
            handleCurrentWeatherData(it)
        }

        val hourlyListDataObserver = Observer<List<OneHourData>> {
            handleHourlyListData(it)
        }

        viewModel.apply {
            currentWeatherData.observe(viewLifecycleOwner, currentWeatherObserver)
            oneDaysData.observe(viewLifecycleOwner, hourlyListDataObserver)
        }

    }

    private fun handleCurrentWeatherData(currentWeather: CurrentWeather?){
        currentWeather?.let{
            mainCardBinding.apply {
                cityName.text = it.cityName
                currentTemp.text = getString(R.string.temp_with_symbol, it.temperature)
                dateTime.text = it.observationTime
                forecast.text = it.weatherDescriptions
                highLow.text = getString(R.string.dummy_high_low)
                feelsLike.text = getString(R.string.feels_like_temp, it.feelslike)
                Glide.with(this@HourlyListFragment)
                    .load(it.weatherIcon)
                    .error(R.drawable.ic_sun)
                    .into(image)
            }
        }

    }

    private fun handleHourlyListData(oneHourList: List<OneHourData>?){
        val sortedList = oneHourList?.sortedBy { it.exactHour.substring(0,2).toInt() }
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val shiftedList = mutableListOf<OneHourData>()
        // shift list so it will be sorted starting from current hour
        sortedList?.let{
            if (it.size == 24){
                for (i in currentHour+1 until it.size){
                    shiftedList.add(sortedList[i])
                }
                for (i in 0 until currentHour){
                    shiftedList.add(sortedList[i])
                }
            }else{
                Log.e(TAG, "handleHourlyListData: list size != 24 (${it.size})")
            }
        }
        sortedList?.let {
            hourlyListAdapter.submitList(shiftedList)
        }
    }

}