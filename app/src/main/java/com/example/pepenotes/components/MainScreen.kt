package com.example.pepenotes.components


import android.widget.CalendarView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pepenotes.MainViewModel
import com.example.pepenotes.R
import java.time.OffsetDateTime


@Composable
fun MainScreen(
    mainViewModel: MainViewModel = viewModel(factory = MainViewModel.factory),
) {
    val calendarState = remember { mutableStateOf(false) }
    val itemsList = mainViewModel.itemsList.collectAsState(initial = emptyList())
    val isFiltered = mainViewModel.isFiltered.collectAsState(initial = false)
    Scaffold(topBar = {
        TopBar(calendarState = calendarState)
    }) { contentPadding ->
        Box (Modifier.fillMaxSize()) {
            if (calendarState.value) {
                DatePickerCustom { y, m, d ->
                    mainViewModel.getByDate(
                        OffsetDateTime.of(y, m, d, 0, 0, 0, 0, OffsetDateTime.now().offset)
                    )
                    calendarState.value = false
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .padding(contentPadding)
                        .fillMaxHeight()
                ) {
                    items(itemsList.value) { item ->
                        ListItem(card = item,
                            { checkBox -> mainViewModel.update(item.copy(checkBox = checkBox)) },
                            { text -> mainViewModel.update(item.copy(text = text)) },
                            { mainViewModel.delete(item) })
                    }
                }
                if (isFiltered.value) {
                    IconButton(
                        onClick = {
                            mainViewModel.changeValueByAll()
                        },
                        Modifier.align(Alignment.BottomCenter)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.closed),
                            contentDescription = "clear"
                        )
                    }
                } else {
                    IconButton(
                        onClick = { mainViewModel.insertItem() },
                        Modifier.align(Alignment.BottomEnd)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.add_button),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DatePickerCustom(onClick: (Int, Int, Int) -> Unit) {
    AndroidView(
        { CalendarView(it) },
        modifier = Modifier.wrapContentWidth(),
        update = { views ->
            views.setOnDateChangeListener { _, y, m, d ->
                onClick(y, m + 1, d)
            }
        }
    )
}