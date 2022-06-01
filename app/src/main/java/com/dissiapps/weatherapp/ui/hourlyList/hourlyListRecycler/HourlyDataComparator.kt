package com.dissiapps.weatherapp.ui.hourlyList.hourlyListRecycler

import androidx.recyclerview.widget.DiffUtil
import com.dissiapps.weatherapp.data.local.models.OneHourData

class HourlyDataComparator: DiffUtil.ItemCallback<OneHourData>() {
    override fun areItemsTheSame(oldItem: OneHourData, newItem: OneHourData) =
            oldItem.exactHour == newItem.exactHour

    override fun areContentsTheSame(oldItem: OneHourData, newItem: OneHourData) =
            oldItem == newItem
}