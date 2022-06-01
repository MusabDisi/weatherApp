package com.dissiapps.weatherapp.ui.hourlyList.hourlyListRecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dissiapps.weatherapp.data.local.models.OneHourData
import com.dissiapps.weatherapp.databinding.OneHourListItemBinding

class HourlyListAdapter : ListAdapter<OneHourData, HourlyListViewHolder>(HourlyDataComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyListViewHolder {
        val binding = OneHourListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HourlyListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HourlyListViewHolder, position: Int) {
        val data = getItem(position)
        data?.let {
            holder.bind(it)
        }
    }
}