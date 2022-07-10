package com.example.holybibleapp.data.chapters.cloud

import com.example.holybibleapp.core.TypeTokenProvider
import com.google.gson.Gson

interface ChaptersCloudDataSource {

    suspend fun fetchChapters(bookId: Int): List<ChapterCloud>

    class Base(
        private val service: ChaptersService,
        private val gson: Gson,
        private val typeTokenProvider: TypeTokenProvider<List<ChapterCloud>>
    ) : ChaptersCloudDataSource {

        override suspend fun fetchChapters(bookId: Int): List<ChapterCloud> =
            gson.fromJson(service.fetchChapters(bookId).toString(), typeTokenProvider.provideType())

    }
}