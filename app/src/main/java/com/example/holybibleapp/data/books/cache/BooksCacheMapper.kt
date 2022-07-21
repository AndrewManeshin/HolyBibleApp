package com.example.holybibleapp.data.books.cache

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.books.BookData
import com.example.holybibleapp.data.books.CommonBookData
import com.example.holybibleapp.data.books.ToBookMapper

interface BooksCacheMapper : Abstract.Mapper.Data<List<CommonBookData>, List<BookData>> {

    class Base(private val mapper: ToBookMapper) : BooksCacheMapper {
        override fun map(books: List<CommonBookData>) = books.map { bookDB ->
            bookDB.map(mapper)
        }
    }
}