package com.example.holybibleapp.data.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.ToDbMapper
import com.example.holybibleapp.core.DbWrapper
import com.example.holybibleapp.data.chapters.cache.ChapterDataToDbMapper
import com.example.holybibleapp.data.chapters.cache.ChapterDb
import com.example.holybibleapp.domain.chapters.ChapterDomain

class ChapterData(
    private val id: Int,
    private val bookId: Int
) : ToDbMapper<ChapterDb, ChapterDataToDbMapper>,
    Abstract.Object<ChapterDomain, ChapterDataToDomainMapper> {

    override fun map(mapper: ChapterDataToDomainMapper): ChapterDomain = mapper.map(id, bookId)


    override fun mapToDb(mapper: ChapterDataToDbMapper, dbWrapper: DbWrapper<ChapterDb>): ChapterDb =
        mapper.mapToDb(id, bookId, dbWrapper)
}