package com.example.holybibleapp.data.chapters.cloud

import androidx.lifecycle.Transformations.map
import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.chapters.ChapterData
import com.example.holybibleapp.data.chapters.ToChapterMapper

interface ChaptersCloudMapper : Abstract.Mapper {
    fun map(chapters: List<ChapterCloud>, bookId: Int): List<ChapterData>

    class Base(private val chapterMapper: ToChapterMapper) : ChaptersCloudMapper {
        override fun map(chapters: List<ChapterCloud>, bookId: Int) = chapters.map { chapterCloud ->
            ChapterCloudWrapper(chapterCloud, bookId).map(chapterMapper)
        }
    }
}