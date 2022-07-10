package com.example.holybibleapp.data

import com.example.holybibleapp.data.books.BookData
import com.example.holybibleapp.data.books.BooksData
import com.example.holybibleapp.data.books.BooksRepository
import com.example.holybibleapp.data.books.cache.BookDB
import com.example.holybibleapp.data.books.cache.BooksCacheDataSource
import com.example.holybibleapp.data.books.cache.BooksCacheMapper
import com.example.holybibleapp.data.books.cloud.BookCloud
import com.example.holybibleapp.data.books.cloud.BooksCloudDataSource
import com.example.holybibleapp.data.books.cloud.BooksCloudMapper
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.net.UnknownHostException

class BooksRepositoryTest : BaseBooksRepositoryTest() {

    private val unknownHostException = UnknownHostException()

    @Test
    fun test_no_connection_no_cache() = runBlocking {
        val testCloudDataSource = TestBooksCloudDataSource(returnSuccess = false)
        val testCacheDataSource = TestBooksCacheDataSource(returnSuccess = false)
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestToBookMapper()),
            BooksCacheMapper.Base(TestToBookMapper())
        )

        val actual = repository.fetchBooks()
        val expected = BooksData.Fail(unknownHostException)

        assertEquals(expected, actual)

    }

    @Test
    fun test_cloud_success_no_cache() = runBlocking {
        val testCloudDataSource = TestBooksCloudDataSource(returnSuccess = true)
        val testCacheDataSource = TestBooksCacheDataSource(returnSuccess = false)
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestToBookMapper()),
            BooksCacheMapper.Base(TestToBookMapper())
        )

        val actual = repository.fetchBooks()
        val expected = BooksData.Success(
            listOf(
                BookData(0, "name0", "ot"),
                BookData(1, "name1", "ot"),
                BookData(2, "name2", "ot"),
            )
        )

        assertEquals(expected, actual)
    }

    @Test
    fun test_no_connection_with_cache() = runBlocking {
        val testCloudDataSource = TestBooksCloudDataSource(returnSuccess = false)
        val testCacheDataSource = TestBooksCacheDataSource(returnSuccess = true)
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestToBookMapper()),
            BooksCacheMapper.Base(TestToBookMapper())
        )

        val actual = repository.fetchBooks()
        val expected = BooksData.Success(
            listOf(
                BookData(10, "name10", "nt"),
                BookData(11, "name11", "nt"),
                BookData(12, "name12", "nt"),
            )
        )

        assertEquals(expected, actual)
    }

    @Test
    fun test_cloud_success_with_cache() = runBlocking {
        val testCloudDataSource = TestBooksCloudDataSource(returnSuccess = true)
        val testCacheDataSource = TestBooksCacheDataSource(returnSuccess = true)
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestToBookMapper()),
            BooksCacheMapper.Base(TestToBookMapper())
        )

        val actual = repository.fetchBooks()
        val expected = BooksData.Success(
            listOf(
                BookData(10, "name10", "nt"),
                BookData(11, "name11", "nt"),
                BookData(12, "name12", "nt"),
            )
        )

        assertEquals(expected, actual)
    }

    private inner class TestBooksCloudDataSource(
        private val returnSuccess: Boolean,
    ) : BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> {
            if (returnSuccess) {
                return listOf(
                    BookCloud(0, "name0", "ot"),
                    BookCloud(1, "name1", "ot"),
                    BookCloud(2, "name2", "ot"),
                )
            } else {
                throw unknownHostException
            }
        }
    }

    private inner class TestBooksCacheDataSource(
        private val returnSuccess: Boolean,
    ) : BooksCacheDataSource {

        override fun fetchBooks(): List<BookDB> {
            return if (returnSuccess) {
                listOf(
                    BookDB().apply {
                        id = 10
                        name = "name10"
                        testament = "nt"
                    },
                    BookDB().apply {
                        id = 11
                        name = "name11"
                        testament = "nt"
                    },
                    BookDB().apply {
                        id = 12
                        name = "name12"
                        testament = "nt"
                    }
                )
            } else {
                emptyList()
            }
        }

        override fun saveBooks(books: List<BookData>) {
            // not used here
        }
    }
}