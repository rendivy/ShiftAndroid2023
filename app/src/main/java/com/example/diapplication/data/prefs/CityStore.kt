package com.example.diapplication.data.prefs

import android.content.SharedPreferences
import javax.inject.Inject

class CityStore @Inject constructor (private val sharedPreferences: SharedPreferences) {

    fun saveCity(city: String) {
        sharedPreferences.edit().putString("currentPlace", city).apply()
    }

    fun getCity(): String? {
        return sharedPreferences.getString("currentPlace", null)
    }

    fun removeCity() {
        sharedPreferences.edit().remove("currentPlace").apply()
    }

    fun hasCity(): Boolean {
        return sharedPreferences.contains("currentPlace")
    }


}