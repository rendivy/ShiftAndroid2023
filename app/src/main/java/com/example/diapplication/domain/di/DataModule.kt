package com.example.diapplication.domain.di

import com.example.diapplication.data.remote.WeatherApiService
import com.example.diapplication.domain.common.Constants
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
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

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    private val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideDatabase(): FirebaseDatabase{
        return Firebase.database
    }

    @get:Provides
    val json = Json {
        ignoreUnknownKeys = true
    }
    @Provides
    @Singleton
    fun provideWeatherApiService(okHttpClient: OkHttpClient): WeatherApiService = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build().create(WeatherApiService::class.java)

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = okHttpClient

    @Provides
    @Singleton
    fun provideCitiesReference(database: FirebaseDatabase) = database.getReference("cities")

}