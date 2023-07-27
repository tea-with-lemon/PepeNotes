package com.example.picker

import android.app.Application
import com.example.picker.domain.DataBase

class App: Application() {
    val dataBase by lazy {DataBase.createDataBase(this)}
}