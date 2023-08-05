package com.example.diapplication.data

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SettingsManager {
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val darkThemeRef: DatabaseReference = database.getReference("darkTheme")


    fun saveDarkTheme(darkTheme: Boolean) {
        darkThemeRef.setValue(darkTheme)
    }

    fun getDarkThemeState(onResult: (Boolean) -> Unit) {
        darkThemeRef.get().addOnSuccessListener { snapshot ->
            val isDarkTheme = snapshot.getValue(Boolean::class.java) ?: false
            onResult(isDarkTheme)
        }.addOnFailureListener {
            onResult(false)
        }
    }


}