package com.example.pepenotes

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.pepenotes.components.SearchField
import com.example.pepenotes.domain.DataBase
import com.example.pepenotes.domain.Note
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.OffsetDateTime

//в этом классе мы хотим записывать, считывать, получать данные, для этого нам нужен экземпляр нашей базы данных,
//но класс ViewModel особенный, он не знает про контекст и передать его в конструктор нельзя, поэтому этот класс
//инициализируется с помощью специального класса Factory

@Suppress("UNCHECKED_CAST")
class MainViewModel(val database: DataBase, private val savedStateHandle: SavedStateHandle) :
    ViewModel() {
    @OptIn(ExperimentalCoroutinesApi::class)
    val itemsList =
    //в getstateflow отслеживаем значения по ключу all, нам прилетают значения потоком Flow,
        // flatmap преобразует значение в Flow<Value>
        savedStateHandle.getStateFlow<Int>("currentState", 1)
            .flatMapLatest { flag ->
                if (flag == 1) {
                    database.dao.getAll()
                } else if (flag == 2) {
                    //а тут отслеживаем значения по ключу date
                    savedStateHandle.getStateFlow<Long>("date", 0)
                        .flatMapLatest { date ->
                            val odt = OffsetDateTime.ofInstant(
                                Instant.ofEpochMilli(date),
                                OffsetDateTime.now().offset
                            )
                            database.dao.getByDate(odt)
                        }
                } else if (flag == 3) {
                    savedStateHandle.getStateFlow<String?>("search", "").flatMapLatest {
                        query -> database.dao.searching(query)
                    }

                } else {
                    database.dao.getAll()
                }
            }

    val isFilteredByDate = savedStateHandle.getStateFlow("currentState", 1).map { flag ->
        flag == 2
    }

    fun insertItem() = viewModelScope.launch {
        val note = Note()
        database.dao.insert(note)
    }

    fun update(note: Note) = viewModelScope.launch {
        database.dao.insert(note)
    }

    fun delete(note: Note) = viewModelScope.launch {
        database.dao.delete(note)
    }

    fun getByDate(date: OffsetDateTime) = viewModelScope.launch {
        savedStateHandle["date"] = date.toInstant().toEpochMilli()
        savedStateHandle["currentState"] = 2
    }

    fun changeValueByAll() {
        savedStateHandle["date"] = 0
        savedStateHandle["currentState"] = 1
    }

    fun searching(query: String) {
        savedStateHandle["search"] = query
        savedStateHandle["currentState"] = 3
    }

    companion object {
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val database = (checkNotNull(extras[APPLICATION_KEY]) as App).dataBase
                return MainViewModel(database, SavedStateHandle.createHandle(null, null)) as T
            }
        }
    }
}