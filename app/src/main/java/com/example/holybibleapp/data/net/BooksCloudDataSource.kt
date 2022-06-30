package com.example.holybibleapp.data.net

interface BooksCloudDataSource {

    suspend fun fetchBooks(): List<BookCloud>

    class Base(private val service: BooksService) : BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> {
            return service.fetchBooks()
        }
    }
}