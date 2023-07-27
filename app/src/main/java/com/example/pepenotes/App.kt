package com.example.pepenotes

import android.app.Application
import com.example.pepenotes.domain.DataBase

class App: Application() {
    val dataBase by lazy {DataBase.createDataBase(this)}
}