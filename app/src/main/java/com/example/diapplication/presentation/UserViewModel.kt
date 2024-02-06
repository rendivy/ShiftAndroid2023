package com.example.diapplication.presentation


/*
@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserDataRepository) : ViewModel() {
    private val _darkTheme = MutableStateFlow(true)
    val darkTheme: StateFlow<Boolean> = _darkTheme

    fun updateUserTheme(condition: Boolean) {
        viewModelScope.launch {
            _darkTheme.value = condition
            repository.updateUserTheme(_darkTheme)
        }

    }

    fun getUserTheme() {
        viewModelScope.launch {
            repository.getUserTheme(_darkTheme)
        }
    }
}*/
