package com.example.holybibleapp.presentation

import com.example.holybibleapp.R
import com.example.holybibleapp.domain.BookDomainToUIMapper
import com.example.holybibleapp.domain.ErrorType
import org.junit.Assert.*
import org.junit.Test

/**
 * Test for [BaseBooksDomainToUIMapper]
 */
class BaseBooksDomainToUIMapperTest {
    @Test
    fun test_fail() {
        val resourceProvider = TestResourceProvider()
        val mapper = BaseBooksDomainToUIMapper(resourceProvider, object: BookDomainToUIMapper {
            override fun map(id: Int, name: String): BookUI {
                throw IllegalStateException("Not used here")
            }
        })
        var actual = mapper.map(ErrorType.NO_CONNECTION)
        var expected = BooksUI.Base(listOf(BookUI.Fail("noConnection")))
        assertEquals(expected, actual)

        actual = mapper.map(ErrorType.SERVICE_UNAVAILABLE)
        expected = BooksUI.Base(listOf(BookUI.Fail("serviceUnavailable")))
        assertEquals(expected, actual)

        actual = mapper.map(ErrorType.GENERIC_ERROR)
        expected = BooksUI.Base(listOf(BookUI.Fail("somethingWentWrong")))
        assertEquals(expected, actual)
    }

    private inner class TestResourceProvider() : ResourceProvider {
        override fun getString(id: Int) = when (id) {
            R.string.no_connection_message -> "noConnection"
            R.string.service_unavailable_message -> "serviceUnavailable"
            else -> "somethingWentWrong"
        }
    }
}