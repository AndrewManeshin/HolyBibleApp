package com.example.holybibleapp.data.books.cache

import com.example.holybibleapp.core.DbWrapper
import com.example.holybibleapp.core.Read
import com.example.holybibleapp.core.RealmProvider
import com.example.holybibleapp.core.Save
import com.example.holybibleapp.data.books.BookData
import io.realm.Realm

interface BooksCacheDataSource : Save<List<BookData>>, Read<List<BookDb>> {

    class Base(
        private val realmProvider: RealmProvider,
        private val mapper: BookDataToDbMapper
    ) : BooksCacheDataSource {

        override fun read(): List<BookDb> {
            return realmProvider.provide().use { realm ->
                val booksDB = realm.where(BookDb::class.java).findAll() ?: emptyList()
                return realm.copyFromRealm(booksDB)
            }
        }

        override fun save(data: List<BookData>) {
            realmProvider.provide().use { realm ->
                realm.executeTransaction {
                    data.forEach { book ->
                        book.mapBy(mapper, BookDbWrapper(it))
                    }
                }

            }
        }

        private inner class BookDbWrapper(realm: Realm) : DbWrapper.Base<BookDb>(realm) {
            override fun dbClass() = BookDb::class.java
        }
    }
}