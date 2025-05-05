package com.shreyash.simplecompose.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.shreyash.simplecompose.R
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.shreyash.simplecompose.presentation.home.BottomNavBar
import com.shreyash.simplecompose.presentation.home.bottomNavItems
import com.shreyash.simplecompose.presentation.home.tabs.ChatTabScreen
import com.shreyash.simplecompose.presentation.home.tabs.HomeTabScreen
import com.shreyash.simplecompose.presentation.home.tabs.NotificationTabScreen
import com.shreyash.simplecompose.presentation.home.tabs.SocialTabScreen
import com.shreyash.simplecompose.presentation.login.LoginScreen
import kotlinx.coroutines.launch

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
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
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
                        "profile" -> {
                            // Navigate to profile or handle profile action
                        }
                        "settings" -> {
                            // Navigate to settings or handle settings action
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    currentRoute: String?,
    onMenuClick: () -> Unit, 
    onSearchClick: () -> Unit
) {
    TopAppBar(
        title = { 
            if (currentRoute == bottomNavItems[0].route) {
                // Show logo for home screen
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    // Replace R.drawable.company_logo with your actual logo resource
                    // If you don't have a logo yet, you can use a placeholder icon
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "Company Logo",
                        modifier = Modifier.size(40.dp)
                    )
                }
            } else {
                // Show text title for other tabs
                val title = when (currentRoute) {
                    bottomNavItems[1].route -> "Social"
                    bottomNavItems[2].route -> "Chat"
                    bottomNavItems[3].route -> "Notification"
                    else -> "Simple Compose"
                }
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile Menu",
                    modifier = Modifier.size(28.dp)
                )
            }
        },
        actions = {
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier.size(28.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}

@Composable
fun SideMenu(onItemClick: (String) -> Unit) {
    ModalDrawerSheet {
        // Drawer header with user info
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Profile image
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                        .clickable { onItemClick("profile") },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Profile Picture",
                        tint = Color.White,
                        modifier = Modifier.size(70.dp)
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // User name
                Text(
                    text = "User Name",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                
                // User email
                Text(
                    text = "user@example.com",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Drawer menu items
        NavigationDrawerItem(
            icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = false,
            onClick = { onItemClick("profile") }
        )
        
        NavigationDrawerItem(
            icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
            label = { Text("Settings") },
            selected = false,
            onClick = { onItemClick("settings") }
        )
    }
}
