package com.dissiapps.weatherapp.ui.dailyList.dailyListRecycler

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dissiapps.weatherapp.R
import com.dissiapps.weatherapp.data.local.models.OneDaysData
import com.dissiapps.weatherapp.databinding.OneDayListItemBinding

class DailyListViewHolder(
    private val binding: OneDayListItemBinding
):
RecyclerView.ViewHolder(
    binding.root
){
    fun bind(oneDaysData: OneDaysData){
        binding.apply {
            dayName.text = oneDaysData.dayName

            Glide.with(itemView)
                .load(oneDaysData.dayIconUrl)
                .error(R.drawable.ic_sun)
                .into(dayIcon)

            Glide.with(itemView)
                .load(oneDaysData.nightIconUrl)
                .error(R.drawable.ic_crescent)
                .into(nightIcon)

            highLow.text = itemView.context.getString(R.string.high_low_temps, oneDaysData.high, oneDaysData.low)
        }
    }
}