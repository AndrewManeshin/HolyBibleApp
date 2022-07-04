//package com.example.holybibleapp.data
//
//import com.example.holybibleapp.core.Book
//import com.example.holybibleapp.data.cache.BookCacheMapper
//import com.example.holybibleapp.data.cache.BookDB
//
//abstract class BaseBooksRepositoryTest {
//    protected class TestBookMapper: ToBookMapper {
//        override fun map(id: Int, name: String) = Book(id, name)
//    }
//
//    protected class TestBookCacheMapper : ToBookMapper {
//        override fun map(id: Int, name: String): Book = Book(id, name)
//    }
//}