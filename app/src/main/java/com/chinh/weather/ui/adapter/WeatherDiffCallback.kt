package com.chinh.weather.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.chinh.weather.data.model.WeatherInfo

class WeatherDiffCallback(
    private val oldList: List<WeatherInfo>,
    private val newList: List<WeatherInfo>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {

        return oldList[oldPosition] == newList[newPosition]
    }

}