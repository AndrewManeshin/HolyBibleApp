package com.example.holybibleapp.domain

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.Book
import com.example.holybibleapp.presentation.BooksUI
import retrofit2.HttpException
import java.net.UnknownHostException

sealed class BooksDomain : Abstract.Object<BooksUI, BooksDomainToUIMapper>() {
    class Success(private val books: List<Book>) : BooksDomain() {
        override fun map(mapper: BooksDomainToUIMapper) = mapper.map(books)
    }

    class Fail(private val e: Exception) : BooksDomain() {
        override fun map(mapper: BooksDomainToUIMapper) = mapper.map(
            when (e) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        )
    }
}