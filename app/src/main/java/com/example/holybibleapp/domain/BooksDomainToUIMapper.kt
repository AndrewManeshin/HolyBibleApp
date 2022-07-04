package com.example.holybibleapp.domain

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.presentation.BooksUI

interface BooksDomainToUIMapper : Abstract.Mapper {
    fun map(books: List<BookDomain>) : BooksUI
    fun map(errorType: ErrorType) : BooksUI
}