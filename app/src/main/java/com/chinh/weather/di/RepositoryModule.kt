package com.chinh.weather.di

import com.chinh.weather.repository.WeatherRepository
import com.chinh.weather.repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideNewsRepository(repository: WeatherRepositoryImpl): WeatherRepository {
        return repository
    }
}