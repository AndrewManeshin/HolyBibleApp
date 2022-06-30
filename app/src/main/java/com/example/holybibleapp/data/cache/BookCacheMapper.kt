package com.example.holybibleapp.data.cache

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.Book

interface BookCacheMapper : Abstract.Mapper {

    fun map(bookDB: BookDB): Book

    class Base() : BookCacheMapper {
        override fun map(bookDB: BookDB): Book = Book(bookDB.id, bookDB.name)
    }
}
