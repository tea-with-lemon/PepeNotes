package com.example.pepenotes.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pepenotes.domain.Note


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteFragment(
    note: Note,
    onContentChange: (String, String, Note) -> Unit,
    onTitleChanged: (String, Note) -> Unit
) {
    val cursorPos = remember { mutableStateOf(TextRange(note.text.length)) }
    val colors = TextFieldDefaults.textFieldColors(
        disabledTextColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
    )
    Column {
        Title(colors = colors, note = note, onTitleChanged)
        Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
        TextField(
            value = TextFieldValue(note.text, cursorPos.value),
            onValueChange = {
                onContentChange(it.text, it.text.substringBefore('\n'), note)
                cursorPos.value = it.selection
            },
            Modifier
                .fillMaxSize()
                .padding(all = 10.dp)
                .border(
                    border = BorderStroke(1.dp, color = Color.DarkGray),
                    shape = RoundedCornerShape(5.dp)
                ),
            textStyle = TextStyle(
                fontSize = 18.sp
            ),
            colors = colors,
            label = { Text("Текст") }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Title(colors: TextFieldColors, note: Note, onTitleChanged: (String, Note) -> Unit) {
    TextField(
        value = note.title,
        onValueChange = { onTitleChanged(it, note) },
        Modifier
            .padding(all = 10.dp)
            .fillMaxWidth()
            .border(
                border = BorderStroke(1.dp, color = Color.DarkGray),
                shape = RoundedCornerShape(5.dp)
            ),
        colors = colors,
        label = { Text("Название") }
    )
}