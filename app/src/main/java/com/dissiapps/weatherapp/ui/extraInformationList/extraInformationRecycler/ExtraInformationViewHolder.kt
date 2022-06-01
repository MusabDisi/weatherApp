package com.dissiapps.weatherapp.ui.extraInformationList.extraInformationRecycler

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dissiapps.weatherapp.data.local.models.ExtraInfo
import com.dissiapps.weatherapp.databinding.ExtraInfoListItemBinding
import com.dissiapps.weatherapp.utils.ExtraInfoItemsIds
import com.dissiapps.weatherapp.utils.getUVRiskOfHarm
import com.dissiapps.weatherapp.utils.getValuePercent
import com.dissiapps.weatherapp.utils.getWindDisplayString

class ExtraInformationViewHolder(
    private val binding: ExtraInfoListItemBinding
): RecyclerView.ViewHolder(
binding.root
){
    fun bind(extraInfo: ExtraInfo){
        binding.apply {

            infoTitle.text = extraInfo.infoTitle

            infoValue.text = when(extraInfo.id){
                ExtraInfoItemsIds.UV_ID -> getUVRiskOfHarm(extraInfo.infoValue.toInt())
                ExtraInfoItemsIds.WIND_ID -> getWindDisplayString(itemView.context, extraInfo.infoValue.toInt(), "E") //TODO: get Direction
                ExtraInfoItemsIds.HUMIDITY_ID -> extraInfo.infoValue
                else -> extraInfo.infoValue
            }

            Glide.with(itemView)
                .load(extraInfo.iconDrawable)
                .into(icon)
        }
    }
}