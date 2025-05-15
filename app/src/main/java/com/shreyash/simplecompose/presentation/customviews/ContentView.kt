package com.shreyash.simplecompose.presentation.customviews

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shreyash.simplecompose.R

@Composable
fun CustomContentView() {
    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        Text(
            text = "Title of caption",
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.images),
                contentDescription = null,
                modifier = Modifier
                    .heightIn(min = 150.dp, max = 200.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(
                        0.2.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(12.dp)
                    ), // Border with same shape
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop
            )
        }

        ContentBottomActionView()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFEEEEEE)
@Composable
fun CustomContentViewPreview() {
    CustomContentView()
}