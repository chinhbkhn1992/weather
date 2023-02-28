package com.chinh.weather.ui.weather

import com.chinh.weather.data.model.WeatherInfo

interface WeatherListView {
    fun showLoading(loading: Boolean)
    fun handleResult(data: List<WeatherInfo>?)
    fun handleError(exception: String?)
}