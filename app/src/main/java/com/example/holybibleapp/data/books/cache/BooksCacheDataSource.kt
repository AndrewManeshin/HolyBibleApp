package com.example.holybibleapp.data.books.cache

import com.example.holybibleapp.core.DbWrapper
import com.example.holybibleapp.core.RealmProvider
import com.example.holybibleapp.data.books.BookData
import com.example.holybibleapp.data.books.BookDataToDBMapper
import io.realm.Realm

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
                        book.mapToDb(mapper, BookDbWrapper(it))
                    }
                }

            }
        }

        private inner class BookDbWrapper(realm: Realm) : DbWrapper.Base<BookDB>(realm) {
            override fun dbClass() = BookDB::class.java
        }
    }
}