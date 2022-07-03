package com.chinh.weather.ui.weather
import com.chinh.weather.data.model.WeatherInfo
import com.chinh.weather.data.model.WeatherResponse

interface WeatherListView {
    fun showLoading(loading: Boolean)
    fun handleResult(data: List<WeatherInfo>?)
    fun handleError(exception: String?)
}