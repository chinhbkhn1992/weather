package com.chinh.weather.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chinh.weather.databinding.ItemWeatherBinding
import com.chinh.weather.data.model.WeatherInfo

class WeatherListAdapter(val onclick:(item:WeatherInfo)->Unit) : RecyclerView.Adapter<NewsListViewHolder>() {
    private val items = arrayListOf<WeatherInfo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemWeatherBinding.inflate(inflater, parent, false)
        return NewsListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        holder.setData(items[position])
        holder.binding.root.setOnClickListener {
            onclick.invoke(items[holder.bindingAdapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    fun setItems(items: List<WeatherInfo>) {
        val diffCallback = WeatherDiffCallback(this.items, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.items.clear()
        this.items.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }
}


class NewsListViewHolder(val binding: ItemWeatherBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun setData(item: WeatherInfo) {
        binding.item = item
    }
}