package com.example.picker.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//просто окно ожидания
@Composable
@Preview
fun LoaderComponent() {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
        Text(
            text = "Loading...",
            Modifier
                .align(Alignment.BottomCenter)
                .padding(all = 32.dp),
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 22.sp,
        )
    }
}