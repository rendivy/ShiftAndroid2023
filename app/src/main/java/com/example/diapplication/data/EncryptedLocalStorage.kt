package com.example.diapplication.data

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.diapplication.common.Constants.TOKEN_REFERENCES
import javax.inject.Singleton


@Singleton
class EncryptedLocalStorage(context: Context) {

    private val masterKey: MasterKey =
        MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()

    private val encryptedSharedPreferences =
        EncryptedSharedPreferences.create(
            context, TOKEN_REFERENCES, masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )


    fun saveCurrentCoordinates(coordinate: String) {
        encryptedSharedPreferences.edit().putString("coordinates", coordinate).apply()
    }

    fun getCurrentCoordinates(): String? {
        return encryptedSharedPreferences.getString("coordinates", null)

    }

    fun removeCurrentCoordinates() {
        encryptedSharedPreferences.edit().remove("coordinates").apply()
    }

    fun hasCurrentCoordinates(): Boolean {
        return encryptedSharedPreferences.contains("coordinates")
    }

}