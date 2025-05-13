package com.shreyash.simplecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shreyash.simplecompose.data.repository.UserPreferencesRepository
import com.shreyash.simplecompose.presentation.MainScreen
import com.shreyash.simplecompose.presentation.login.LoginScreen
import com.shreyash.simplecompose.presentation.login.LoginViewModel
import com.shreyash.simplecompose.presentation.navigation.NavRoutes
import com.shreyash.simplecompose.ui.theme.SimpleComposeTheme
import com.shreyash.simplecompose.ui.util.TransparentSystemBars
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Main entry point of the application.
 * Uses Hilt for dependency injection.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    @Inject
    lateinit var userPreferencesRepository: UserPreferencesRepository
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleComposeApp(userPreferencesRepository)
        }
    }
}

/**
 * Root composable that sets up the app theme and navigation.
 */
@Composable
fun SimpleComposeApp(userPreferencesRepository: UserPreferencesRepository) {
    SimpleComposeTheme {
        // Make system bars transparent
        TransparentSystemBars()
        
        val navController = rememberNavController()
        
        // Get the login state from DataStore
        val isLoggedIn by userPreferencesRepository.isLoggedIn.collectAsState(initial = false)
        
        // Set up the navigation host with a remembered NavController
        NavHost(
            navController = navController,
            startDestination = if (isLoggedIn) NavRoutes.MAIN else NavRoutes.LOGIN
        ) {
            composable(NavRoutes.LOGIN) {
                LoginScreen(
                    onSuccess = {
                        navController.navigate(NavRoutes.MAIN) {
                            popUpTo(NavRoutes.LOGIN) { inclusive = true }
                        }
                    }
                )
            }
            
            composable(NavRoutes.MAIN) {
                MainScreen(
                    onLogout = {
                        navController.navigate(NavRoutes.LOGIN) {
                            popUpTo(NavRoutes.MAIN) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}