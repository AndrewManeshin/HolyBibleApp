package com.example.holybibleapp.presentation


import com.example.holybibleapp.domain.BookDomain
import com.example.holybibleapp.domain.BookDomainToUIMapper
import com.example.holybibleapp.domain.BooksDomainToUIMapper
import com.example.holybibleapp.domain.ErrorType

class BaseBooksDomainToUIMapper(
    private val resourceProvider: ResourceProvider,
    private val bookMapper: BookDomainToUIMapper
) : BooksDomainToUIMapper {
    override fun map(books: List<BookDomain>) = BooksUI.Success(books, bookMapper)
    override fun map(errorType: ErrorType) = BooksUI.Fail(errorType, resourceProvider)
}