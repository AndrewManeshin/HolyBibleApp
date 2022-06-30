package com.example.holybibleapp.domain

import  com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.Book
import com.example.holybibleapp.presentation.BooksUI
import retrofit2.HttpException
import java.net.UnknownHostException

//todo rename to BooksDomain

sealed class BookDomain : Abstract.Object<BooksUI, BooksDomainToUIMapper>() {
    class Success(private val books: List<Book>) : BookDomain() {
        override fun map(mapper: BooksDomainToUIMapper) = mapper.map(books)
    }

    class Fail(private val e: Exception) : BookDomain() {
        override fun map(mapper: BooksDomainToUIMapper) = mapper.map(
            when (e) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        )
    }
}