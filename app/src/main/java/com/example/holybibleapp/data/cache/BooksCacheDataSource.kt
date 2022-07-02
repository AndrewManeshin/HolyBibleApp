package com.example.holybibleapp.data.cache

import com.example.holybibleapp.core.Book
import io.realm.Realm

interface BooksCacheDataSource {

    fun fetchBooks(): List<BookDB>

    fun saveBooks(books: List<Book>)

    class Base(private val realmProvider: RealmProvider) : BooksCacheDataSource {

        override fun fetchBooks(): List<BookDB> {
            return realmProvider.provide().use { realm ->
                val booksDB = realm.where(BookDB::class.java).findAll() ?: emptyList()
                return realm.copyFromRealm(booksDB)
            }
        }

        override fun saveBooks(books: List<Book>) {
            realmProvider.provide().use { realm ->
                realm.executeTransaction {
                    books.forEach { book->
                            val bookDB = it.createObject(BookDB::class.java, book.id)
                        bookDB.name = book.name

                    }
                }

            }
        }
    }
}