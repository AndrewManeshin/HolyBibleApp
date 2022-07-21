package com.example.holybibleapp.presentation.books

import com.example.holybibleapp.domain.books.BookDomainToUiMapper
import com.example.holybibleapp.domain.books.BooksDomainToUiMapper
import com.example.holybibleapp.core.ErrorType
import com.example.holybibleapp.core.ResourceProvider
import com.example.holybibleapp.domain.books.BookDomain

class BaseBooksDomainToUiMapper(
    resourceProvider: ResourceProvider,
    private val bookMapper: BookDomainToUiMapper
) : BooksDomainToUiMapper(resourceProvider) {

    override fun map(data: List<BookDomain>) = BooksUi.Base(data.map {
        it.map(bookMapper)
    })

    override fun map(errorType: ErrorType) =
        BooksUi.Base(listOf(BookUi.Fail(errorMessage(errorType))))
}