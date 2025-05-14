package com.shreyash.simplecompose.presentation.home.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.shreyash.simplecompose.presentation.customviews.ContentView

@Composable
fun HomeTabScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Text("Home Tab Content", style = MaterialTheme.typography.headlineMedium)
        ContentView()

    }
}

@Preview(showBackground = true)
@Composable
fun HomeTabScreenPreview() {
    HomeTabScreen()
}