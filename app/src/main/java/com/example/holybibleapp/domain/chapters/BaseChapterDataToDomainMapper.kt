package com.example.holybibleapp.domain.chapters

import com.example.holybibleapp.data.chapters.ChapterDataToDomainMapper

class BaseChapterDataToDomainMapper : ChapterDataToDomainMapper {
    override fun map(id: Int, bookId: Int): ChapterDomain = ChapterDomain.Base(id, bookId)
}