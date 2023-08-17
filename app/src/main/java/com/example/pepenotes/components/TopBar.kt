package com.example.pepenotes.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pepenotes.R

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
                onClick = { },
                Modifier
                    .size(45.dp)
                    .padding(start = 10.dp, end = 10.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.searching),
                    contentDescription = "search_by_word"
                )
            }
            IconButton(
                onClick = { calendarState.value = true },
                Modifier
                    .size(45.dp)
                    .padding(start = 10.dp, end = 10.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.calendar_search),
                    contentDescription = "data_search"
                )
            }
        }
    )
}