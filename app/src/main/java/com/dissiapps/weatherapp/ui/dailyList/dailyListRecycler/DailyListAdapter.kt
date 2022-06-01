package com.dissiapps.weatherapp.ui.dailyList.dailyListRecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dissiapps.weatherapp.data.local.models.OneDaysData
import com.dissiapps.weatherapp.databinding.OneDayListItemBinding

class DailyListAdapter: ListAdapter<OneDaysData, DailyListViewHolder>(DailyDataComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyListViewHolder {
        val binding = OneDayListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DailyListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DailyListViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }
}