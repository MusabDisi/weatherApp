package com.dissiapps.weatherapp.ui.extraInformationList.extraInformationRecycler

import androidx.recyclerview.widget.DiffUtil
import com.dissiapps.weatherapp.data.local.models.ExtraInfo

class ExtraInfoComparator: DiffUtil.ItemCallback<ExtraInfo>() {
    override fun areItemsTheSame(oldItem: ExtraInfo, newItem: ExtraInfo) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ExtraInfo, newItem: ExtraInfo) =
        oldItem == newItem

}