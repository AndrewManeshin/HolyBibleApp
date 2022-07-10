package com.example.holybibleapp.data.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.domain.chapters.ChaptersDomain

interface ChaptersDataToDomainMapper : Abstract.Mapper {

    fun map(chapters: List<ChapterData>): ChaptersDomain
    fun map(exception: Exception): ChaptersDomain
}
