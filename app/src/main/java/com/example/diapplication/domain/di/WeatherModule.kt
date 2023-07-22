package com.example.diapplication.domain.di

import com.example.diapplication.data.remote.WeatherApiService
import com.example.diapplication.data.repository.WeatherRepository
import com.example.diapplication.domain.utils.Constants.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton




@Module
@InstallIn(SingletonComponent::class)
class WeatherModule {

    @get:Provides
    val json = Json {
        ignoreUnknownKeys = true
    }
    @Provides
    @Singleton
    fun provideWeatherApiService(okHttpClient: OkHttpClient): WeatherApiService = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build().create(WeatherApiService::class.java)

    @Singleton
    fun provideRepository(apiService: WeatherApiService): WeatherRepository {
        return WeatherRepository(apiService)
    }

}