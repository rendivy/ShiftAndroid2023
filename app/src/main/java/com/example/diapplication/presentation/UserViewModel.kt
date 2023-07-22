package com.example.diapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diapplication.domain.entity.UserGeocoding
import com.example.diapplication.domain.usecase.UpdateUserGeocodingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val updateUserGeocodingUseCase: UpdateUserGeocodingUseCase
) : ViewModel() {

    private val _userState = MutableStateFlow<UserGeocoding?>(null)
    val userState: StateFlow<UserGeocoding?> = _userState

    fun updateUserInfo(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            try {
                _userState.value = updateUserGeocodingUseCase(latitude, longitude)
            }
            catch (e: Exception) {
                _userState.value = null
            }
        }
    }
}