package com.example.diapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.diapplication.data.database.entity.SavedCities

@Database(entities = [SavedCities::class], version = 1, exportSchema = false)
abstract class CityDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}