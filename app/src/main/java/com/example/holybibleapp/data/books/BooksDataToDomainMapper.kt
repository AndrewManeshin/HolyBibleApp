package com.example.holybibleapp.data.books

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.domain.books.BooksDomain

interface BooksDataToDomainMapper : Abstract.Mapper {

    fun map(books: List<BookData>): BooksDomain
    fun map(exception: Exception): BooksDomain
}