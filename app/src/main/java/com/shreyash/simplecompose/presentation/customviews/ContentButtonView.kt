package com.shreyash.simplecompose.presentation.customviews

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shreyash.simplecompose.R

@Composable
fun ContentButtonView(drawable: Int) {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,            // Background color
            contentColor = Color.White,             // Text/icon color
            disabledContainerColor = Color.Gray,    // Background when disabled
            disabledContentColor = Color.LightGray  // Text/icon when disabled
        ),
        modifier = Modifier
            .size(width = 100.dp, height = 56.dp),
    ) {
        Icon(
            painter = painterResource(id = drawable),
            contentDescription = "Favorite",
            modifier = Modifier
                .size(24.dp),

            )
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewContentButtonView() {
    ContentButtonView(R.drawable.ic_like)
}