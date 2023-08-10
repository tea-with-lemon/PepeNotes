package com.example.pepenotes.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pepenotes.domain.Note
import java.time.format.DateTimeFormatter


@Composable
fun ListItem(
    note: Note,
    onFlagChange: (Boolean) -> Unit,
    onDelete: (Note) -> Unit,
    onClick: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .background(Color.LightGray)
                .padding(all = 10.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = note.date.format(DateTimeFormatter.ofPattern("d MMMM")),
            )
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
            Text(
                text = note.date.format(DateTimeFormatter.ofPattern("YYYY")),
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
            Row(
                Modifier.height(IntrinsicSize.Min)
            ) {
                Checkbox(
                    checked = note.checkBox,
                    onCheckedChange = { onFlagChange(it) },
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Divider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(2.dp),
                    thickness = 5.dp
                )
                Text(
                    text = note.title,
                    Modifier
                        .fillMaxWidth(0.85F)
                        .fillMaxHeight()
                        .clickable { onClick() },
                    fontSize = 20.sp
                )
                Divider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(2.dp),
                    thickness = 5.dp
                )
                IconButton(
                    onClick = { onDelete(note) },
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