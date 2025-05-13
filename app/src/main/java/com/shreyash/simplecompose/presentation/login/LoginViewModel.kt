package com.shreyash.simplecompose.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shreyash.simplecompose.data.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the login screen that uses Hilt for dependency injection
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    // UI state for the login screen
    var uiState by mutableStateOf(LoginUiState())
        private set

    /**
     * Updates the email in the UI state
     */
    fun onEmailChange(newEmail: String) {
        uiState = uiState.copy(email = newEmail)
    }

    /**
     * Updates the password in the UI state
     */
    fun onPasswordChange(newPassword: String) {
        uiState = uiState.copy(password = newPassword)
    }

    /**
     * Performs the login operation
     * In a real app, this would call a repository or use case
     */
    fun login() {
        viewModelScope.launch {
            // Set loading state
            uiState = uiState.copy(isLoading = true, errorMessage = null)

            // Simulate network delay
            delay(1500) // Fake API call

            // Check credentials
            if (uiState.email == "test@example.com" && uiState.password == "123456") {
                // Save login state to DataStore
                userPreferencesRepository.saveLoginState(true, uiState.email)
                
                // Update UI state
                uiState = uiState.copy(isLoading = false, isSuccess = true)
            } else {
                uiState = uiState.copy(isLoading = false, errorMessage = "Invalid credentials")
            }
        }
    }
    
    /**
     * Logs out the user by clearing the login state
     */
    fun logout() {
        viewModelScope.launch {
            userPreferencesRepository.clearLoginState()
            uiState = uiState.copy(isSuccess = false)
        }
    }
}
