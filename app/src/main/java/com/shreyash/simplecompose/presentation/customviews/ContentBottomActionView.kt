package com.shreyash.simplecompose.presentation.customviews

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shreyash.simplecompose.R


@Composable
fun ContentBottomActionView() {
    Row(modifier = Modifier.fillMaxWidth())
    {
        Icon(
            painter = painterResource(id = R.drawable.ic_like),
            contentDescription = "Favorite",
            Modifier.size(24.dp)
        )
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "Favorite"
        )
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "Favorite"
        )
    }
}

@Preview
@Composable
fun PreviewContentBottomActionView() {
    ContentBottomActionView()
}