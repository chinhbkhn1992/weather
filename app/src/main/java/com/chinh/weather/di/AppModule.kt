package com.chinh.weather.di

import android.content.Context
import com.chinh.weather.BuildConfig
import com.chinh.weather.data.api.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .build()
    }

    @Provides
    fun provideHttpClient(
        @ApplicationContext context: Context,
        builder: OkHttpClient.Builder,
        gson: Gson
    ): OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong()
        return builder
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .cache(Cache(context.cacheDir, cacheSize))
            .addInterceptor(HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .build()
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().setDateFormat("yyyy-MM-dd")
//            .excludeFieldsWithoutExposeAnnotation()
            .setLenient()
            .create()
    }

    @Provides
    fun providerClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }
}