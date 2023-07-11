package com.example.diapplication.di

import com.example.diapplication.data.remote.WeatherApiService
import com.example.diapplication.data.repository.WeatherRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


private val json = Json {
    ignoreUnknownKeys = true
}


@Module
@InstallIn(SingletonComponent::class)
class WeatherModule {
    private companion object {
        const val BASE_URL = "http://api.weatherapi.com/v1/"
        const val CONNECT_TIMEOUT = 10L
        const val READ_TIMEOUT = 10L
        const val WRITE_TIMEOUT = 10L
    }

    @Provides
    @Singleton
    fun provideOkHttpClientWithProgress(): OkHttpClient =
        OkHttpClient().newBuilder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .build()


    @Provides
    @Singleton
    fun provideWeatherApiService(): WeatherApiService = Retrofit.Builder()
        .client(provideOkHttpClientWithProgress())
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build().create(WeatherApiService::class.java)


    @Singleton
    fun provideRepository(apiService: WeatherApiService): WeatherRepository {
        return WeatherRepository(apiService)
    }

}