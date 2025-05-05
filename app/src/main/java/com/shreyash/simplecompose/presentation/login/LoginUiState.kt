package com.shreyash.simplecompose.presentation.login

data class LoginUiState (
    val email: String = "test@example.com",
    val password: String = "123456",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false

)