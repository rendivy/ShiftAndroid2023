package com.example.diapplication.domain.di

import com.example.diapplication.data.remote.WeatherApiService
import com.example.diapplication.data.repository.WeatherRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton




@Module
@InstallIn(SingletonComponent::class)
class DomainModule {



    @Singleton
    fun provideRepository(apiService: WeatherApiService): WeatherRepository {
        return WeatherRepository(apiService)
    }

}