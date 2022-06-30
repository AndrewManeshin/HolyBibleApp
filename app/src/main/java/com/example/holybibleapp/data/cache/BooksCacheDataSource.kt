package com.example.holybibleapp.data.cache

import com.example.holybibleapp.core.Book
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query

interface BooksCacheDataSource {

    fun fetchBooks(): List<BookDB>

    fun saveBooks(books: List<Book>)

    class Base(private val config: RealmProvider) : BooksCacheDataSource {

        override fun fetchBooks(): List<BookDB> {
            return Realm.open(config.provide()).query<BookDB>().find()
        }

        override fun saveBooks(books: List<Book>) {
            Realm.open(config.provide()).writeBlocking {
                books.forEach { book ->
                    this.copyToRealm(BookDB().apply {
                        id = book.id
                        name = book.name
                    })
                }
            }
        }
    }
}