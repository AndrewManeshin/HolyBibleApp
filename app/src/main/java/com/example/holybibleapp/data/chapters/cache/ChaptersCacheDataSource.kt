package com.example.holybibleapp.data.chapters.cache

import com.example.holybibleapp.core.DbWrapper
import com.example.holybibleapp.core.RealmProvider
import com.example.holybibleapp.data.chapters.ChapterData
import io.realm.Realm

interface ChaptersCacheDataSource {

    fun fetchChapters(bookId: Int) : List<ChapterDb>
    fun saveChapters(bookId: Int, chapters: List<ChapterData>)

    class Base(
        private val realmProvider: RealmProvider,
        private val mapper: ChapterDataToDbMapper
    ) : ChaptersCacheDataSource {

        override fun fetchChapters(bookId: Int): List<ChapterDb> {
            realmProvider.provide().use { realm ->
                val chapters = realm.where(ChapterDb::class.java)
                    .equalTo("bookId", bookId)
                    .findAll()
                return realm.copyFromRealm(chapters)
            }
        }

        override fun saveChapters(bookId: Int, chapters: List<ChapterData>) {
            realmProvider.provide().use { realm ->
                chapters.forEach { chapter ->
                    chapter.mapToDb(mapper, ChapterDbWrapper(realm))
                }
            }
        }

        private inner class ChapterDbWrapper(realm : Realm) : DbWrapper.Base<ChapterDb>(realm) {
            override fun dbClass() = ChapterDb::class.java
        }
    }
}