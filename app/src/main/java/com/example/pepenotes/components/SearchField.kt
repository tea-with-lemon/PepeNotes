package com.example.pepenotes.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(onSearch: (String) -> Unit) {
    val query = remember { mutableStateOf("") }
    SearchBar(
        query = query.value,
        onQueryChange = {
            query.value = it
            onSearch(it)
        },
        onSearch = onSearch,
        active = false,
        onActiveChange = { },
        Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        placeholder = { Text(text = "Search") }
    ) {

    }
}