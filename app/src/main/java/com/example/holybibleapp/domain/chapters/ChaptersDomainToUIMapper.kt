package com.example.holybibleapp.domain.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.ErrorType
import com.example.holybibleapp.presentation.chapters.ChaptersUI

interface ChaptersDomainToUIMapper : Abstract.Mapper {
    fun map(chapters: List<ChapterDomain>) : ChaptersUI
    fun map(errorType: ErrorType) : ChaptersUI
}