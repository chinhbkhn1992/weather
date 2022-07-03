package com.chinh.weather.repository

import com.chinh.weather.data.model.WeatherInfo
import com.chinh.weather.data.model.WeatherResponse
import com.chinh.weather.repository.model.ApiResult
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
     fun getWeather(query: String): Flow<ApiResult<List<WeatherInfo>>>
}