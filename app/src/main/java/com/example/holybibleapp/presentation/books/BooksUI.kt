package com.example.holybibleapp.presentation.books

import com.example.holybibleapp.core.Abstract

sealed class BooksUI : Abstract.Object<Unit, BooksCommunication> {

    data class Base(private val books: List<BookUI>) : BooksUI() {
        override fun map(mapper: BooksCommunication) = mapper.map(books)
    }
}