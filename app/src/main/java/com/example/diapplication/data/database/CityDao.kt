package com.example.diapplication.data.database

import androidx.room.Dao
import androidx.room.Query
import com.example.diapplication.data.database.entity.SavedCities

@Dao
interface CityDao {

    @Query("SELECT * FROM SavedCities")
    fun getAllCities(): List<SavedCities>

    @Query("SELECT * FROM SavedCities WHERE cityName = :cityName")
    fun getCity(cityName: String): SavedCities

    @Query("DELETE FROM SavedCities WHERE cityName = :cityName")
    fun deleteCity(cityName: String)

    @Query("INSERT INTO SavedCities (cityName) VALUES (:cityName)")
    fun insertCity(cityName: String)

}