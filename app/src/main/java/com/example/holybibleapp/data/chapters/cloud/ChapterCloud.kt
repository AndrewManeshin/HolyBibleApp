package com.example.holybibleapp.data.chapters.cloud

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.chapters.ChapterData
import com.example.holybibleapp.data.chapters.ToChapterMapper
import com.example.holybibleapp.data.chapters.cache.ChapterDb
import com.google.gson.annotations.SerializedName

data class ChapterCloud(
    @SerializedName("id")
    private val id: Int
) : Abstract.Object<ChapterData, ToChapterMapper> {
    override fun map(mapper: ToChapterMapper) = mapper.map(id, 0) // todo

    fun map(bookId: Int, mapper: ToChapterMapper) = mapper.map(id, bookId)
}
//todo 
data class ChapterCloudWrapper(
    private val chapterCloud: ChapterCloud,
    private val bookId: Int
) : Abstract.Object<ChapterData, ToChapterMapper> {
    override fun map(mapper: ToChapterMapper) = chapterCloud.map(bookId, mapper)
}
