package com.example.holybibleapp.domain.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.ErrorType
import com.example.holybibleapp.presentation.chapters.ChaptersUI

sealed class ChaptersDomain : Abstract.Object<ChaptersUI, ChaptersDomainToUIMapper> {
    data class Success(private val chapters: List<ChapterDomain>) : ChaptersDomain() {
        override fun map(mapper: ChaptersDomainToUIMapper) = mapper.map(chapters)
    }

    data class Fail(private val errorType: ErrorType) : ChaptersDomain() {
        override fun map(mapper: ChaptersDomainToUIMapper) = mapper.map(errorType)
    }
}