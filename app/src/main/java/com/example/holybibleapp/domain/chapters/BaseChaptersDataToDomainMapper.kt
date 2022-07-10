package com.example.holybibleapp.domain.chapters

import com.example.holybibleapp.data.chapters.ChapterData
import com.example.holybibleapp.data.chapters.ChapterDataToDomainMapper
import com.example.holybibleapp.data.chapters.ChaptersDataToDomainMapper
import com.example.holybibleapp.core.ErrorType
import retrofit2.HttpException
import java.net.UnknownHostException

class BaseChaptersDataToDomainMapper(
    private val chapterMapper: ChapterDataToDomainMapper
) : ChaptersDataToDomainMapper {

    override fun map(chapters: List<ChapterData>): ChaptersDomain = ChaptersDomain.Success(
        chapters.map { chapterData ->
            chapterData.map(chapterMapper)
        }
    )


    override fun map(exception: Exception) = ChaptersDomain.Fail(
        when (exception) {
            is UnknownHostException -> ErrorType.NO_CONNECTION
            is HttpException -> ErrorType.SERVICE_UNAVAILABLE
            else -> ErrorType.GENERIC_ERROR
        }
    )
}