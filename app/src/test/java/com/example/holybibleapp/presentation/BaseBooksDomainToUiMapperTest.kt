package com.example.holybibleapp.presentation

import com.example.holybibleapp.R
import com.example.holybibleapp.domain.books.BookDomainToUiMapper
import com.example.holybibleapp.core.ErrorType
import com.example.holybibleapp.core.ResourceProvider
import com.example.holybibleapp.presentation.books.BaseBooksDomainToUiMapper
import com.example.holybibleapp.presentation.books.BookUI
import com.example.holybibleapp.presentation.books.BooksUi
import org.junit.Assert.*
import org.junit.Test

/**
 * Test for [BaseBooksDomainToUiMapper]
 */
class BaseBooksDomainToUiMapperTest {
    @Test
    fun test_fail() {
        val resourceProvider = TestResourceProvider()
        val mapper = BaseBooksDomainToUiMapper(resourceProvider, object: BookDomainToUiMapper {
            override fun map(id: Int, name: String): BookUI {
                throw IllegalStateException("Not used here")
            }
        })
        var actual = mapper.map(ErrorType.NO_CONNECTION)
        var expected = BooksUi.Base(listOf(BookUI.Fail("noConnection")))
        assertEquals(expected, actual)

        actual = mapper.map(ErrorType.SERVICE_UNAVAILABLE)
        expected = BooksUi.Base(listOf(BookUI.Fail("serviceUnavailable")))
        assertEquals(expected, actual)

        actual = mapper.map(ErrorType.GENERIC_ERROR)
        expected = BooksUi.Base(listOf(BookUI.Fail("somethingWentWrong")))
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