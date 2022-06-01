package com.dissiapps.weatherapp.ui.extraInformationList.extraInformationRecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dissiapps.weatherapp.data.local.models.ExtraInfo
import com.dissiapps.weatherapp.databinding.ExtraInfoListItemBinding

class ExtraInformationListAdapter : ListAdapter<ExtraInfo, ExtraInformationViewHolder>(
    ExtraInfoComparator()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExtraInformationViewHolder {
        val binding = ExtraInfoListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExtraInformationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExtraInformationViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }
}