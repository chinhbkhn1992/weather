package com.chinh.weather.repository

import com.chinh.weather.data.api.ApiService
import com.chinh.weather.data.model.WeatherInfo
import com.chinh.weather.data.model.WeatherResponse
import com.chinh.weather.repository.model.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    val api: ApiService
) : WeatherRepository {
    override fun getWeather(query: String): Flow<ApiResult<List<WeatherInfo>>> {
        return flow {
            emit(ApiResult.Loading(isLoading = true))
            val data: WeatherResponse = api.getWeather(q = query)
            emit(ApiResult.Success(data.list))
            emit(ApiResult.Loading(isLoading = false))
        }
    }
}