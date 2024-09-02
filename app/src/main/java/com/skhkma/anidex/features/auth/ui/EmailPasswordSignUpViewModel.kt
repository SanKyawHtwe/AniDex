package com.skhkma.anidex.features.auth.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skhkma.anidex.features.auth.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class EmailSignUpUiState {
    data object Idle: EmailSignUpUiState()
    data object Loading : EmailSignUpUiState()
    data class Success(val message: String) : EmailSignUpUiState()
    data class Error(val error: String) : EmailSignUpUiState()
}

class EmailPasswordSignUpViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<EmailSignUpUiState> =
        MutableStateFlow(EmailSignUpUiState.Idle)
    val uiState: StateFlow<EmailSignUpUiState> = _uiState

    fun createAccount(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = EmailSignUpUiState.Loading
            authRepository.signUpWithEmailPassword(email, password).fold(
                {
                    _uiState.value = EmailSignUpUiState.Success(it)
                },
                {
                    _uiState.value = EmailSignUpUiState.Error(it.message ?: "Something went wrong!")
                }
            )
        }
    }

}