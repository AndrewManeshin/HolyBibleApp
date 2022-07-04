package com.example.holybibleapp.presentation

import com.example.holybibleapp.R
import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.domain.BookDomain
import com.example.holybibleapp.domain.BookDomainToUIMapper
import com.example.holybibleapp.domain.ErrorType

sealed class BooksUI : Abstract.Object<Unit, BooksCommunication> {

    class Success(
        private val books: List<BookDomain>,
        private val bookMapper: BookDomainToUIMapper
    ) : BooksUI() {
        override fun map(mapper: BooksCommunication) {
            val booksUI = books.map {
                it.map(bookMapper)
            }
            mapper.map(booksUI)
        }
    }

    class Fail(
        private val errorType: ErrorType,
        private val resourceProvider: ResourceProvider
    ) : BooksUI() {
        override fun map(mapper: BooksCommunication) {
            val messageId = when (errorType) {
                ErrorType.NO_CONNECTION -> R.string.no_connection_message
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
                else -> R.string.something_went_wrong
            }
            val massage = resourceProvider.getString(messageId)
            mapper.map(listOf(BookUI.Fail(massage)))
        }
    }
}