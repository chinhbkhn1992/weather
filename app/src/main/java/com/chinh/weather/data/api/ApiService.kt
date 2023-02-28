package com.chinh.weather.data.api

import com.chinh.weather.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("forecast/daily")
    suspend fun getWeather(
        @Query("q") q: String,
        @Query("cnt") sortBy: Int = 7,
        @Query("apiKey") apiKey: String = "60c6fbeb4b93ac653c492ba806fc346d",
    ): WeatherResponse
}