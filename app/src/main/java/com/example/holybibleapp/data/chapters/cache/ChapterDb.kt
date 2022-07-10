package com.example.holybibleapp.data.chapters.cache

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.chapters.ChapterData
import com.example.holybibleapp.data.chapters.ToChapterMapper
import io.realm.RealmObject

open class ChapterDb : RealmObject(), Abstract.Object<ChapterData, ToChapterMapper> {

    var id: Int = -1
    var bookId: Int = -1

    override fun map(mapper: ToChapterMapper) = mapper.map(id, bookId)
}