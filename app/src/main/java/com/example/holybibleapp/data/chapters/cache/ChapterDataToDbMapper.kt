package com.example.holybibleapp.data.chapters.cache

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.DbWrapper
import com.example.holybibleapp.data.chapters.ChapterId

interface ChapterDataToDbMapper : Abstract.Mapper {

    fun mapToDb(chapterId: ChapterId, dbWrapper: DbWrapper<ChapterDb>): ChapterDb

    class Base : ChapterDataToDbMapper {
        override fun mapToDb(chapterId: ChapterId, dbWrapper: DbWrapper<ChapterDb>) =
            chapterId.mapToDb(dbWrapper)
    }
}