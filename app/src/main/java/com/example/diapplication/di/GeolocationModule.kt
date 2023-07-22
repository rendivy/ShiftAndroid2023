package com.example.diapplication.di

import com.example.diapplication.data.remote.ClientGeolocationService
import com.example.diapplication.data.repository.ClientRepository
import com.example.diapplication.data.utils.Constants
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
class GeolocationModule {

    @get:Provides
    val json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient().newBuilder()
            .connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
            .build()


    @Provides
    @Singleton
    fun provideClientGeolocationService(): ClientGeolocationService = Retrofit.Builder()
        .client(provideOkHttpClient())
        .baseUrl("https://api.bigdatacloud.net/data/")
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build().create(ClientGeolocationService::class.java)


    @Singleton
    fun provideClientRepository(apiService: ClientGeolocationService): ClientRepository {
        return ClientRepository(apiService)
    }
}