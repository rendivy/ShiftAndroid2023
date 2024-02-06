package com.example.diapplication.di

import android.content.Context
import androidx.room.Room
import com.example.diapplication.common.NetworkConstants.BASE_URL
import com.example.diapplication.common.NetworkConstants.CONNECT_TIMEOUT
import com.example.diapplication.common.NetworkConstants.READ_TIMEOUT
import com.example.diapplication.common.NetworkConstants.WRITE_TIMEOUT
import com.example.diapplication.data.EncryptedLocalStorage
import com.example.diapplication.data.database.CityDatabase
import com.example.diapplication.data.remote.WeatherApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {


    private val gson: Gson = GsonBuilder().create()

    private val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .build()


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CityDatabase {
        return Room.databaseBuilder(
            context,
            CityDatabase::class.java,
            "city_database"
        ).build()
    }

    @get:Provides
    val json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    fun provideEncryptedSharedPreferences(@ApplicationContext context: Context): EncryptedLocalStorage {
        return EncryptedLocalStorage(context)
    }

    @Provides
    @Singleton
    fun provideWeatherApiService(okHttpClient: OkHttpClient): WeatherApiService = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build().create(WeatherApiService::class.java)

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = okHttpClient


}