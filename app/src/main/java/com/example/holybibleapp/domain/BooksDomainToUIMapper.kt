package com.example.holybibleapp.domain

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.Book
import com.example.holybibleapp.presentation.BooksUI

interface BooksDomainToUIMapper : Abstract.Mapper {
    fun map(books: List<Book>) : BooksUI
    fun map(errorType: ErrorType) : BooksUI
}