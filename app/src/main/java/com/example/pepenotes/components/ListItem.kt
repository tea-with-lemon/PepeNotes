package com.example.pepenotes.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.pepenotes.domain.Note
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItem(
    card: Note,
    onFlagChange: (Boolean) -> Unit,
    onContentChange: (String) -> Unit,
    onDelete: (Note) -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .background(Color.LightGray)
                .padding(all = 10.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = card.date.format(DateTimeFormatter.ofPattern("d MMMM")),
            )
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
            Text(
                text = card.date.format(DateTimeFormatter.ofPattern("YYYY")),
            )
        }

        Card(
            modifier = Modifier
                .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(5.dp),
            border = BorderStroke(1.dp, color = Color.DarkGray),
            shape = RoundedCornerShape(5.dp)
        ) {
            val cursorPos = remember { mutableStateOf(TextRange(card.text.length)) }
            Row(
                Modifier.height(IntrinsicSize.Min)
            ) {
                Checkbox(
                    checked = card.checkBox,
                    onCheckedChange = { onFlagChange(it) },
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Divider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(2.dp),
                    thickness = 5.dp
                )
                TextField(
                    value = TextFieldValue(card.text, cursorPos.value),
                    onValueChange = {
                        onContentChange(it.text)
                        cursorPos.value = it.selection
                    },
                    Modifier.fillMaxWidth(0.85F)
                )
                Divider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(2.dp),
                    thickness = 5.dp
                )
                IconButton(
                    onClick = { onDelete(card) },
                    Modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }
            }
        }
        Divider(
            modifier = Modifier
                .padding(vertical = 10.dp),
            thickness = 5.dp,
        )
    }
}