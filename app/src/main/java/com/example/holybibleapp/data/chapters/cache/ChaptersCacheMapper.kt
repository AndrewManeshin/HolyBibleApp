package com.example.holybibleapp.data.chapters.cache

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.chapters.ChapterData
import com.example.holybibleapp.data.chapters.ToChapterMapper

interface ChaptersCacheMapper : Abstract.Mapper {
    fun map(chapters: List<ChapterDb>) : List<ChapterData>

    class Base(private val mapper: ToChapterMapper) : ChaptersCacheMapper {
        override fun map(chapters: List<ChapterDb>) = chapters.map { chapterDb ->
            chapterDb.map(mapper)
        }
    }
}