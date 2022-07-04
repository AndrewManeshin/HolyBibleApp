package com.example.holybibleapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.holybibleapp.domain.BooksDomainToUIMapper
import com.example.holybibleapp.domain.BooksInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val booksInteractor: BooksInteractor,
    private val mapper: BooksDomainToUIMapper,
    private val communication: BooksCommunication
) : ViewModel() { //todo interface

    fun fetchBooks() {

        communication.map(listOf(BookUI.Progress))

        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = booksInteractor.fetchBooks()
            val resultUI = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                resultUI.map(communication)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<BookUI>>) {
        communication.observe(owner, observer)
    }
}