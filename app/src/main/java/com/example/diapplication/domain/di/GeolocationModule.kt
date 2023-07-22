package com.example.diapplication.domain.di

import com.example.diapplication.data.remote.ClientGeolocationService
import com.example.diapplication.data.repository.ClientRepository
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
class GeolocationModule {

    @get:Provides
    val json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideClientGeolocationService(okHttpClient: OkHttpClient): ClientGeolocationService =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.bigdatacloud.net/data/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build().create(ClientGeolocationService::class.java)


    @Singleton
    fun provideClientRepository(apiService: ClientGeolocationService): ClientRepository {
        return ClientRepository(apiService)
    }
}