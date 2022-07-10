package com.example.holybibleapp.data.books.cache

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.books.BookData
import com.example.holybibleapp.data.books.ToBookMapper

interface BooksCacheMapper : Abstract.Mapper {

    fun map(books: List<Abstract.Object<BookData, ToBookMapper>>): List<BookData>

    class Base(private val mapper: ToBookMapper) : BooksCacheMapper {
        override fun map(books: List<Abstract.Object<BookData, ToBookMapper>>): List<BookData> =
            books.map { bookDB ->
                bookDB.map(mapper)
            }
    }
}