package com.example.diapplication.data.remote

import com.example.diapplication.data.UserGeocoding
import retrofit2.http.GET
import retrofit2.http.Query

interface ClientGeolocationService {
    @GET("reverse-geocode-client")
    suspend fun getCurrentGeolocation(
        @Query("latitude") latitude: Double?,
        @Query("longitude") longitude: Double?,
    ): UserGeocoding
}