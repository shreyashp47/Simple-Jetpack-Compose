package com.shreyash.simplecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.shreyash.simplecompose.presentation.AppNavHost
import com.shreyash.simplecompose.ui.theme.SimpleComposeTheme
import com.shreyash.simplecompose.ui.util.TransparentSystemBars
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main entry point of the application.
 * Uses Hilt for dependency injection.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleComposeApp()
        }
    }
}

/**
 * Root composable that sets up the app theme and navigation.
 */
@Composable
fun SimpleComposeApp() {
    SimpleComposeTheme {
        // Make system bars transparent
        TransparentSystemBars()
        
        // Set up the navigation host with a remembered NavController
        // to prevent unnecessary recompositions
        AppNavHost(navController = rememberNavController())
    }
}