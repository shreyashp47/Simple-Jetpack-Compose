package com.shreyash.simplecompose.presentation.home

import androidx.compose.ui.graphics.vector.ImageVector

// Data class to represent each screen in the bottom navigation
data class BottomNavItem(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)