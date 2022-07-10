package com.example.holybibleapp.domain.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.presentation.chapters.ChapterUi

sealed class ChapterDomain : Abstract.Object<ChapterUi, ChapterDomainToUIMapper> {

    data class Base(
        private val id: Int,
        private val bookId: Int
    ) : ChapterDomain() {
        override fun map(mapper: ChapterDomainToUIMapper) = mapper.map(id, bookId)
    }
}