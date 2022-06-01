package com.dissiapps.weatherapp.ui.hourlyList.hourlyListRecycler

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dissiapps.weatherapp.R
import com.dissiapps.weatherapp.data.local.models.OneHourData
import com.dissiapps.weatherapp.databinding.OneHourListItemBinding

class HourlyListViewHolder(
    private val binding: OneHourListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(oneHourData: OneHourData) {
        binding.apply {
            exactHour.text = oneHourData.exactHour
            hourlyTemp.text = oneHourData.temp
            Glide.with(itemView)
                .load(oneHourData.iconUrl)
                .error(R.drawable.ic_sun)
                .into(hourlyIcon)
        }
    }
}