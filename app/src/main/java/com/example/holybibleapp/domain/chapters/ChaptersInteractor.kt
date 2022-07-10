package com.example.holybibleapp.domain.chapters

import com.example.holybibleapp.data.chapters.ChaptersDataToDomainMapper
import com.example.holybibleapp.data.chapters.ChaptersRepository

interface ChaptersInteractor {

    suspend fun fetchChapters(bookId: Int): ChaptersDomain

    class Base(
        private val chaptersRepository: ChaptersRepository,
        private val mapper: ChaptersDataToDomainMapper
    ) : ChaptersInteractor {
        override suspend fun fetchChapters(bookId: Int) = chaptersRepository.fetchChapters(bookId).map(mapper)
    }
}