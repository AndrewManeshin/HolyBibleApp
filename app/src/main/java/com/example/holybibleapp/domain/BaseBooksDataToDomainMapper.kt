package com.example.holybibleapp.domain

import com.example.holybibleapp.core.Book
import com.example.holybibleapp.data.BooksDataToDomainMapper

class BaseBooksDataToDomainMapper : BooksDataToDomainMapper {
    override fun map(books: List<Book>) = BookDomain.Success(books)
    override fun map(exception: Exception) = BookDomain.Fail(exception)
}