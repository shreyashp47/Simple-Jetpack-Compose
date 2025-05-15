package com.shreyash.simplecompose.presentation.customviews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.shreyash.simplecompose.R


@Composable
fun ContentBottomActionView() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {
        ContentButtonView(R.drawable.ic_like)
        ContentButtonView(R.drawable.ic_comment)
        ContentButtonView(R.drawable.ic_bookmark)
        ContentButtonView(R.drawable.new_ic_share)

    }
}

@Preview
@Composable
fun PreviewContentBottomActionView() {
    ContentBottomActionView()
}