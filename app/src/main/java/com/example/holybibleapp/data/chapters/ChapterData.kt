package com.example.holybibleapp.data.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.DbWrapper
import com.example.holybibleapp.data.chapters.cache.ChapterDataToDbMapper
import com.example.holybibleapp.data.chapters.cache.ChapterDb
import com.example.holybibleapp.domain.chapters.ChapterDomain

class ChapterData(
    private val chapterId: ChapterId,
) : Abstract.Object.ToDb<ChapterDb, ChapterDataToDbMapper>,
    Abstract.Object<ChapterDomain, ChapterDataToDomainMapper> {

    override fun map(mapper: ChapterDataToDomainMapper) = mapper.map(chapterId)

    override fun mapBy(mapper: ChapterDataToDbMapper, dbWrapper: DbWrapper<ChapterDb>): ChapterDb =
        mapper.mapToDb(chapterId, dbWrapper)
}