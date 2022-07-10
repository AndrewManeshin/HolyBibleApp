package com.example.holybibleapp.data.chapters.cache

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.DbWrapper

interface ChapterDataToDbMapper  : Abstract.Mapper {
    fun mapToDb(id: Int, bookId: Int, dbWrapper: DbWrapper<ChapterDb>) : ChapterDb

    class Base : ChapterDataToDbMapper {
        override fun mapToDb(id: Int, bookId: Int, dbWrapper: DbWrapper<ChapterDb>): ChapterDb {
            val chapterDb = dbWrapper.createObject()
            chapterDb.id = id
            chapterDb.bookId = bookId
            return chapterDb
        }
    }
}