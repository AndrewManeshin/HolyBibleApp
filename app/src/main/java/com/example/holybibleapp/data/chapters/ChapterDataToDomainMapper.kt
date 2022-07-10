package com.example.holybibleapp.data.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.domain.chapters.ChapterDomain

interface ChapterDataToDomainMapper : Abstract.Mapper {
    fun map(id: Int, bookId: Int): ChapterDomain

    class Base() : ChapterDataToDomainMapper {
        override fun map(id: Int, bookId: Int): ChapterDomain {
            TODO("Not yet implemented")
        }
    }
}