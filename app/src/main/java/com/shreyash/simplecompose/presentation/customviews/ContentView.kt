package com.shreyash.simplecompose.presentation.customviews

import androidx.annotation.ContentView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ContentView() {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text("Content View", style = MaterialTheme.typography.headlineMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewContentView() {
    ContentView()
}