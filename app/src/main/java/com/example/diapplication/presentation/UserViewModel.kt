package com.example.diapplication.presentation

import androidx.lifecycle.ViewModel
import com.example.diapplication.data.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject



@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserDataRepository): ViewModel() {
    private val _darkTheme = MutableStateFlow(false)
    val darkTheme: StateFlow<Boolean> = _darkTheme


    fun updateUserTheme(condition: Boolean) {
        _darkTheme.value = condition
        repository.updateUserTheme(_darkTheme)
    }

    fun getUserTheme() {
        repository.getUserTheme(_darkTheme)
    }




}