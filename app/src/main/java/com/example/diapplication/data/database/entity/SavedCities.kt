package com.example.diapplication.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SavedCities(
    @PrimaryKey
    var cityName: String,
)
