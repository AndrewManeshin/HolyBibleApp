package com.example.holybibleapp.domain.books

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.ErrorType
import com.example.holybibleapp.presentation.books.BooksUI

sealed class BooksDomain : Abstract.Object<BooksUI, BooksDomainToUIMapper> {
    data class Success(private val books: List<BookDomain>) : BooksDomain() {
        override fun map(mapper: BooksDomainToUIMapper) = mapper.map(books)
    }

    data class Fail(private val errorType: ErrorType) : BooksDomain() {
        override fun map(mapper: BooksDomainToUIMapper) = mapper.map(errorType)
    }
}