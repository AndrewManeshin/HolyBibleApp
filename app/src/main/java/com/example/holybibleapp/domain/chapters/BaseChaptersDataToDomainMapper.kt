package com.example.holybibleapp.domain.chapters

import com.example.holybibleapp.data.chapters.ChapterData
import com.example.holybibleapp.data.chapters.ChapterDataToDomainMapper
import com.example.holybibleapp.data.chapters.ChaptersDataToDomainMapper

class BaseChaptersDataToDomainMapper(
    private val chapterMapper: ChapterDataToDomainMapper
) : ChaptersDataToDomainMapper() {

    override fun map(data: List<ChapterData>): ChaptersDomain = ChaptersDomain.Success(
        data.map { chapterData ->
            chapterData.map(chapterMapper)
        }
    )

    override fun map(e: Exception) = ChaptersDomain.Fail(errorType(e))
}