package com.dissiapps.weatherapp.ui.dailyList.dailyListRecycler

import androidx.recyclerview.widget.DiffUtil
import com.dissiapps.weatherapp.data.local.models.OneDaysData

class DailyDataComparator: DiffUtil.ItemCallback<OneDaysData>() {
    override fun areItemsTheSame(oldItem: OneDaysData, newItem: OneDaysData) =
        oldItem.dayName == newItem.dayName

    override fun areContentsTheSame(oldItem: OneDaysData, newItem: OneDaysData) =
        oldItem == newItem

}