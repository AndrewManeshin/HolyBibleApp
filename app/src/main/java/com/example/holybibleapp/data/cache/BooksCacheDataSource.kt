package com.example.holybibleapp.data.cache

import com.example.holybibleapp.data.BookData
import com.example.holybibleapp.data.BookDataToDBMapper

interface BooksCacheDataSource {

    fun fetchBooks(): List<BookDB>

    fun saveBooks(books: List<BookData>)

    class Base(
        private val realmProvider: RealmProvider,
        private val mapper: BookDataToDBMapper
    ) : BooksCacheDataSource {

        override fun fetchBooks(): List<BookDB> {
            return realmProvider.provide().use { realm ->
                val booksDB = realm.where(BookDB::class.java).findAll() ?: emptyList()
                return realm.copyFromRealm(booksDB)
            }
        }

        override fun saveBooks(books: List<BookData>) {
            realmProvider.provide().use { realm ->
                realm.executeTransaction {
                    books.forEach { book ->
                        book.mapTo(mapper, DBWrapper.Base(it))
                    }
                }

            }
        }
    }
}