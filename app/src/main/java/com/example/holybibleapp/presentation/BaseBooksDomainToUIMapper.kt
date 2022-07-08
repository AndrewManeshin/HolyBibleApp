package com.example.holybibleapp.presentation


import com.example.holybibleapp.R
import com.example.holybibleapp.domain.BookDomain
import com.example.holybibleapp.domain.BookDomainToUIMapper
import com.example.holybibleapp.domain.BooksDomainToUIMapper
import com.example.holybibleapp.domain.ErrorType

class BaseBooksDomainToUIMapper(
    private val resourceProvider: ResourceProvider,
    private val bookMapper: BookDomainToUIMapper
) : BooksDomainToUIMapper {
    override fun map(books: List<BookDomain>) = BooksUI.Base(books.map {
        it.map(bookMapper)
    })

    override fun map(errorType: ErrorType) : BooksUI {
        val messageId = when (errorType) {
            ErrorType.NO_CONNECTION -> R.string.no_connection_message
            ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
            else -> R.string.something_went_wrong
        }
        val massage = resourceProvider.getString(messageId)
        return BooksUI.Base(listOf(BookUI.Fail(massage)))
    }
}