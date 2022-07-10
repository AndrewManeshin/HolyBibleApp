package com.example.holybibleapp.domain.books

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.ErrorType
import com.example.holybibleapp.presentation.books.BooksUI

interface BooksDomainToUIMapper : Abstract.Mapper {
    fun map(books: List<BookDomain>) : BooksUI
    fun map(errorType: ErrorType) : BooksUI
}