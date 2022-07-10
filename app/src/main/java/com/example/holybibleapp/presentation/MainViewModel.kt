package com.example.holybibleapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.holybibleapp.domain.books.BooksDomainToUIMapper
import com.example.holybibleapp.domain.books.BooksInteractor
import com.example.holybibleapp.presentation.books.BookUI
import com.example.holybibleapp.presentation.books.BooksCommunication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val booksInteractor: BooksInteractor,
    private val mapper: BooksDomainToUIMapper,
    private val communication: BooksCommunication,
    private val uiDataCache: UiDataCache
) : ViewModel() { //todo interface

    fun fetchBooks() {

        communication.map(listOf(BookUI.Progress))

        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = booksInteractor.fetchBooks()
            val resultUI = resultDomain.map(mapper)
            val actual = resultUI.cache(uiDataCache)
            withContext(Dispatchers.Main) {
                actual.map(communication)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<BookUI>>) {
        communication.observe(owner, observer)
    }

    fun collapseOrExpand(id: Int) {
        communication.map(uiDataCache.changeState(id))
    }

    fun saveCollapsedStates() {
        uiDataCache.saveState()
    }
}