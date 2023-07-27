package com.example.picker.components

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.picker.R
import com.example.picker.ui.theme.Purple40

//первая проба Jetpack Compose -- экран ошибки
@Composable
fun ErrorScreen() {
    val colors = listOf(Color(0xFFD0BCFF), Color(0xFFEFB8C8))
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.bunny1),
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .align(Alignment.CenterHorizontally)
                .border(
                    BorderStroke(
                        width = 3.dp,
                        brush = Brush.horizontalGradient(colors = colors),
                    ), shape = RoundedCornerShape(percent = 20)
                )
                .clip(RoundedCornerShape(percent = 20)),
            contentDescription = null,
        )

        Text(
            text = "Ooops",
            fontStyle = FontStyle.Italic,
            fontSize = 40.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        ClickableText(
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        fontSize = 20.sp,
                        color = Purple40,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append("click")
                }
            },
            onClick = {
                Toast.makeText(
                    context,
                    "Good",
                    Toast.LENGTH_LONG
                ).show()
            },
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )

    }
    Box {
        Button(
            onClick = { },
            contentPadding = PaddingValues(horizontal = 20.dp),
            shape = RoundedCornerShape(size = 55.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.back),
                contentDescription = null,
                modifier = Modifier
                    .size(25.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}