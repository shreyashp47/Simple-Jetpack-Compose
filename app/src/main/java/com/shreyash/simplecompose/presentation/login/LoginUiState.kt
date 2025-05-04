package com.shreyash.simplecompose.presentation.login

data class LoginUiState (
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false

)