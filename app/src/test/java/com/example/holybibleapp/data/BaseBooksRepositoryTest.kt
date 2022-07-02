package com.example.holybibleapp.data

import com.example.holybibleapp.core.Book
import com.example.holybibleapp.data.cache.BookCacheMapper
import com.example.holybibleapp.data.cache.BookDB
import com.example.holybibleapp.data.net.BookCloudMapper

abstract class BaseBooksRepositoryTest {
    protected class TestBookCloudMapper: BookCloudMapper {
        override fun map(id: Int, name: String) = Book(id, name)
    }

    protected class TestBookCacheMapper : BookCacheMapper {
        override fun map(bookDB: BookDB): Book = Book(bookDB.id, bookDB.name)
    }
}