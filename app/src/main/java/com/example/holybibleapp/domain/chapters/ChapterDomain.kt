package com.example.holybibleapp.domain.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.presentation.chapters.ChapterUI

sealed class ChapterDomain : Abstract.Object<ChapterUI, ChapterDomainToUIMapper> {

    data class Base(
        private val id: Int,
        private val bookId: Int
    ) : ChapterDomain() {
        override fun map(mapper: ChapterDomainToUIMapper) = mapper.map(id, bookId)
    }
}