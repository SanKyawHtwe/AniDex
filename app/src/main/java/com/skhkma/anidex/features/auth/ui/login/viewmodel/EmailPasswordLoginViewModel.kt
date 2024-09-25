package com.skhkma.anidex.features.auth.ui.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skhkma.anidex.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class EmailPasswordLoginUiState {
    data object Idle: EmailPasswordLoginUiState()
    data object Loading : EmailPasswordLoginUiState()
    data class Success(val message: String) : EmailPasswordLoginUiState()
    data class Error(val error: String) : EmailPasswordLoginUiState()

}

class EmailPasswordLoginViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<EmailPasswordLoginUiState> =
        MutableStateFlow(EmailPasswordLoginUiState.Idle)
    val uiState: StateFlow<EmailPasswordLoginUiState> = _uiState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = EmailPasswordLoginUiState.Loading
            authRepository.loginWithEmailPassword(email, password).fold(
                {
                    _uiState.value = EmailPasswordLoginUiState.Success(it)

                },
                {
                    _uiState.value =
                        EmailPasswordLoginUiState.Error(it.message ?: "Something went wrong!")
                }
            )
        }
    }



    fun setUiStateToIdle() {
        _uiState.value = EmailPasswordLoginUiState.Idle
    }

}
