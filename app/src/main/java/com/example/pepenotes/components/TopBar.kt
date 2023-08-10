package com.example.pepenotes.components

import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(calendarState: MutableState<Boolean>, showFullNote: MutableState<Boolean>) {
    TopAppBar(
        title = { Text("Notes") },
        backgroundColor = Color.White,
        actions = {
            if (calendarState.value || showFullNote.value) {
                IconButton(
                    onClick = {
                        calendarState.value = false
                        showFullNote.value = false
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back"
                    )
                }
            }
            IconButton(
                onClick = { calendarState.value = true }
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search"
                )
            }
        }
    )
}