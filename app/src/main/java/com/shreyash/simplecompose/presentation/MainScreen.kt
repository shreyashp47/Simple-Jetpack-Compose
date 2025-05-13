package com.shreyash.simplecompose.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.shreyash.simplecompose.presentation.components.AppTopBar
import com.shreyash.simplecompose.presentation.components.SideMenu
import com.shreyash.simplecompose.presentation.home.BottomNavBar
import com.shreyash.simplecompose.presentation.home.bottomNavItems
import com.shreyash.simplecompose.presentation.home.tabs.ChatTabScreen
import com.shreyash.simplecompose.presentation.home.tabs.HomeTabScreen
import com.shreyash.simplecompose.presentation.home.tabs.NotificationTabScreen
import com.shreyash.simplecompose.presentation.home.tabs.SocialTabScreen
import com.shreyash.simplecompose.presentation.login.LoginViewModel
import com.shreyash.simplecompose.presentation.navigation.NavRoutes
import kotlinx.coroutines.launch

/**
 * Main screen that contains the bottom navigation and drawer
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onLogout: () -> Unit = {}
) {
    val loginViewModel: LoginViewModel = hiltViewModel()
    val bottomNavController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Get current route to determine which screen is active
    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SideMenu(
                onItemClick = { route ->
                    scope.launch {
                        drawerState.close()
                    }
                    // Handle navigation to different sections from drawer
                    when (route) {
                        NavRoutes.PROFILE -> {
                            // Navigate to profile or handle profile action
                        }

                        NavRoutes.SETTINGS -> {
                            // Navigate to settings or handle settings action
                        }

                        NavRoutes.LOGOUT -> {
                            // Handle logout action
                            loginViewModel.logout()
                            onLogout()
                        }
                    }
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                AppTopBar(
                    currentRoute = currentRoute,
                    onMenuClick = {
                        scope.launch {
                            if (drawerState.isClosed) drawerState.open() else drawerState.close()
                        }
                    },
                    onSearchClick = {
                        // Handle search click
                    }
                )
            },
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