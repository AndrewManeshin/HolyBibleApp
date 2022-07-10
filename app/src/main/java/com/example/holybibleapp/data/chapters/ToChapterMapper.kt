package com.example.holybibleapp.data.chapters

import com.example.holybibleapp.core.Abstract

interface ToChapterMapper : Abstract.Mapper {
    fun map(id: Int, bookId: Int) : ChapterData

    class Base : ToChapterMapper {
        override fun map(id: Int, bookId: Int) = ChapterData(id, bookId)
    }
}