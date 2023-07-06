package com.example.diapplication.di

import com.example.diapplication.data.remote.WeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object WeatherModule{
    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApiService{
        return Retrofit.Builder().baseUrl("http://api.weatherapi.com/v1/")
            .build().create(WeatherApiService::class.java)
    }
}