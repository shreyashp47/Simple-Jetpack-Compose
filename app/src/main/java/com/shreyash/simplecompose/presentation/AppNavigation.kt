package com.shreyash.simplecompose.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.shreyash.simplecompose.presentation.home.BottomNavBar
import com.shreyash.simplecompose.presentation.home.tabs.HomeTabScreen
import com.shreyash.simplecompose.presentation.home.bottomNavItems
import com.shreyash.simplecompose.presentation.home.tabs.ChatTabScreen
import com.shreyash.simplecompose.presentation.home.tabs.NotificationTabScreen
import com.shreyash.simplecompose.presentation.home.tabs.SocialTabScreen
import com.shreyash.simplecompose.presentation.login.LoginScreen

/**
 * Main navigation component that uses Hilt for ViewModel injection
 */
@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = "login") {
        composable("login") {
            // Using hiltViewModel() for ViewModel injection
            LoginScreen(
                // ViewModel is injected by default parameter in LoginScreen
                onSuccess = {
                    navController.navigate("main") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
        
        // Main navigation graph that contains the home screen with bottom navigation
        navigation(
            startDestination = "home",
            route = "main"
        ) {
            composable("home") {
                val bottomNavController = rememberNavController()
                
                Scaffold(
                    bottomBar = {
                        BottomNavBar(navController = bottomNavController)
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = bottomNavController,
                        startDestination = bottomNavItems[0].route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(bottomNavItems[0].route) {
                            HomeTabScreen()
                        }
                        
                        composable(bottomNavItems[1].route) {
                            SocialTabScreen()
                        }
                        
                        composable(bottomNavItems[2].route) {
                            ChatTabScreen()
                        }
                        
                        composable(bottomNavItems[3].route) {
                            NotificationTabScreen()
                        }
                    }
                }
            }
        }
    }
}
