package com.example.diapplication.data.repository


/*
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

}*/
