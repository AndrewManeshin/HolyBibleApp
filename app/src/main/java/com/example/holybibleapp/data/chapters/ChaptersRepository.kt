package com.example.holybibleapp.data.chapters

import com.example.holybibleapp.data.chapters.cache.ChaptersCacheDataSource
import com.example.holybibleapp.data.chapters.cache.ChaptersCacheMapper
import com.example.holybibleapp.data.chapters.cloud.ChaptersCloudDataSource
import com.example.holybibleapp.data.chapters.cloud.ChaptersCloudMapper

interface ChaptersRepository {

    suspend fun fetchChapters(bookId: Int) : ChaptersData

    class Base(
        private val cloudDataSource: ChaptersCloudDataSource,
        private val cacheDataSource: ChaptersCacheDataSource,
        private val chapterCloudMapper: ChaptersCloudMapper,
        private val chapterCacheMapper: ChaptersCacheMapper
    ) : ChaptersRepository {
        override suspend fun fetchChapters(bookId: Int) = try {
            val chaptersCacheList = cacheDataSource.fetchChapters(bookId)
            if (chaptersCacheList.isEmpty()) {
                val chaptersCloudList = cloudDataSource.fetchChapters(bookId)
                val chapters = chapterCloudMapper.map(chaptersCloudList, bookId)
                cacheDataSource.saveChapters(bookId, chapters)
                ChaptersData.Success(chapters)
            } else {
                ChaptersData.Success(chapterCacheMapper.map(chaptersCacheList))
            }
        } catch (e: Exception) {
            ChaptersData.Fail(e)
        }
    }

}