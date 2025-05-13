package com.shreyash.simplecompose.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shreyash.simplecompose.R
import com.shreyash.simplecompose.presentation.home.bottomNavItems


/**
 * Top app bar with menu and search buttons
 */
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
                        painter = painterResource(id = R.drawable.app_icon),
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

@Preview(showBackground = true)
@Composable
fun PreviewAppTopBar_Home() {
    MaterialTheme {
        AppTopBar(
            currentRoute = bottomNavItems[0].route, // Home screen route
            onMenuClick = {},
            onSearchClick = {}
        )
    }
}