package com.example.holybibleapp.domain.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.chapters.ChapterId
import com.example.holybibleapp.presentation.chapters.ChapterUi

data class ChapterDomain(
    private val chapterId: ChapterId
) : Abstract.Object<ChapterUi, ChapterDomainToUiMapper> {
    override fun map(mapper: ChapterDomainToUiMapper) = mapper.map(chapterId)
}