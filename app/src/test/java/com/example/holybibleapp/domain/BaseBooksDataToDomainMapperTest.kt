package com.example.holybibleapp.domain

import com.example.holybibleapp.data.BookData
import com.example.holybibleapp.data.BookDataToDomainMapper
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

/**
 * Test for [BaseBooksDataToDomainMapper]
 */
class BaseBooksDataToDomainMapperTest {

    private val mapper = BaseBooksDataToDomainMapper(object : BookDataToDomainMapper {
        override fun map(id: Int, name: String) = BookDomain.Base(id, name)
    })

    @Test
    fun test_success() {

        val actual = mapper.map(
            listOf(
                BookData(1, "genesis", "ot"),
                BookData(66, "Revelation", "nt"),
            ),
        )

        val data = mutableListOf(
            BookDomain.Testament(TestamentType.OLD),
            BookDomain.Base(1, "genesis"),
            BookDomain.Testament(TestamentType.NEW),
            BookDomain.Base(66, "Revelation"),
        )

        val expected = BooksDomain.Success(data)

        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {

        var actual = mapper.map(UnknownHostException())
        var expected = BooksDomain.Fail(ErrorType.NO_CONNECTION)

        assertEquals(expected, actual)

        actual = mapper.map(
            HttpException(
                Response.error<ResponseBody>(
                    500,
                    "".toResponseBody("plain/text".toMediaType())
                )
            )
        )
        expected = BooksDomain.Fail(ErrorType.SERVICE_UNAVAILABLE)

        assertEquals(expected, actual)

        actual = mapper.map(IllegalStateException())
        expected = BooksDomain.Fail(ErrorType.GENERIC_ERROR)

        assertEquals(expected, actual)
    }
}