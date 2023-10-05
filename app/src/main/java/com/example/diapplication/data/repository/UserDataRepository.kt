package com.example.diapplication.data.repository

import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserDataRepository @Inject constructor(database: FirebaseDatabase) {
    private val themeReference = database.getReference("darkThemeIsEnabled")
    private val tokenReference = database.getReference("token")

    fun getToken(apiToken: MutableStateFlow<String>) {
        tokenReference.get().addOnSuccessListener {
            apiToken.value = it.value as String
        }.addOnFailureListener {
            apiToken.value = ""
        }
    }

    fun updateUserTheme(darkTheme: MutableStateFlow<Boolean>) {
        themeReference.setValue(darkTheme.value)
    }

    fun getUserTheme(darkTheme: MutableStateFlow<Boolean>) {
        themeReference.get().addOnSuccessListener {
            darkTheme.value = it.value as Boolean
        }.addOnFailureListener {
            darkTheme.value = false
        }
    }

}