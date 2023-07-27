package com.example.pepenotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.pepenotes.components.MainScreen
import com.example.pepenotes.ui.theme.PepeNotesTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PepeNotesTheme {
//                val cards: SnapshotStateList<Card> = remember {
//                    mutableStateListOf(
//                        Card("Hello", LocalDate.parse("1998-01-12"), false),
//                        Card("Hello", LocalDate.parse("1997-09-26"), false),
//                        Card("Hello", LocalDate.parse("2002-04-15"), true),
//                        Card("Hello", LocalDate.parse("2023-06-29"), false),
//                        Card("Hello", LocalDate.parse("2023-06-26"), false),
//                        Card("Hello", LocalDate.parse("2023-06-27"), false)
//                    )
//                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                    //CardBoard(cards)
                    //LearningCard()
                    //LoaderComponent()
                    //ErrorScreen(context = applicationContext)
                }
            }
        }
    }
}








