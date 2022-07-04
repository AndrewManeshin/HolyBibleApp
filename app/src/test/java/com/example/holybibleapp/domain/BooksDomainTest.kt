package com.example.holybibleapp.domain

import com.example.holybibleapp.data.BookData
import com.example.holybibleapp.data.BookDataToDomainMapper
import com.example.holybibleapp.presentation.BookUI
import com.example.holybibleapp.presentation.BooksUI
import org.junit.Assert.assertEquals
import org.junit.Test


/**
 * Test for [BooksDomain.Success]
 */
class BooksDomainTest {
    @Test
    fun test_map() {

        val bookMapper = object : BookDomainToUIMapper {
            override fun map(id: Int, name: String): BookUI {
                return BookUI.Base(id, name)
            }
        }

        val domain = BooksDomain.Success(listOf(
            BookData(1, "genesis", "ot"),
            BookData(66, "Revelation", "nt"),
        ),
            object : BookDataToDomainMapper {
                override fun map(id: Int, name: String): BookDomain {
                    return BookDomain.Base(id, name)
                }
            }
        )

        val actual = domain.map(object : BooksDomainToUIMapper {
            override fun map(books: List<BookDomain>): BooksUI {
                return BooksUI.Success(books, bookMapper)
            }

            override fun map(errorType: ErrorType): BooksUI {
                throw IllegalStateException()
            }
        })

        val expected = BooksUI.Success(
            listOf(
                BookDomain.Testament(TestamentType.OLD),
                BookDomain.Base(1, "genesis"),
                BookDomain.Testament(TestamentType.NEW),
                BookDomain.Base(66, "Revelation"),
            ), bookMapper
        )

        assertEquals(expected, actual)
    }
}