package com.example.holybibleapp.presentation

import com.example.holybibleapp.core.Book
import com.example.holybibleapp.domain.BooksDomainToUIMapper
import com.example.holybibleapp.domain.ErrorType

class BaseBookDomainToUIMapper(
    private val booksCommunication: BooksCommunication,
    private val resourceProvider: ResourceProvider
) : BooksDomainToUIMapper {
    override fun map(books: List<Book>) = BooksUI.Success(booksCommunication, books)
    override fun map(errorType: ErrorType) = BooksUI.Fail(booksCommunication, errorType, resourceProvider)
}