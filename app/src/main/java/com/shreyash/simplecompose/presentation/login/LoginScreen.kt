package com.shreyash.simplecompose.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Login screen that uses Hilt for ViewModel injection
 */
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onSuccess: () -> Unit
) {
    val state = viewModel.uiState

    if (state.isSuccess) {
        onSuccess()
        return
    }

    LoginScreenContent(
        state = state,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onLoginClick = viewModel::login
    )
}
