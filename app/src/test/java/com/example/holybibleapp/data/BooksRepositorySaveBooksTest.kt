package com.example.holybibleapp.data

import com.example.holybibleapp.core.Book
import com.example.holybibleapp.data.cache.BookDB
import com.example.holybibleapp.data.cache.BooksCacheDataSource
import com.example.holybibleapp.data.cache.BooksCacheMapper
import com.example.holybibleapp.data.net.BookCloud
import com.example.holybibleapp.data.net.BooksCloudDataSource
import com.example.holybibleapp.data.net.BooksCloudMapper
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BooksRepositorySaveBooksTest : BaseBooksRepositoryTest() {

    @Test
    fun test_save_books() = runBlocking {
        val testCloudDataSource = TestBooksCloudDataSource()
        val testCacheDataSource = TestBooksCacheDataSource()
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestBookCloudMapper()),
            BooksCacheMapper.Base(TestBookCacheMapper())
        )

        val actualCloud = repository.fetchBooks()
        val expectedCloud = BooksData.Success(listOf(
            Book(0, "name0"),
            Book(1, "name1"),
            Book(2, "name2"),
        ))

        assertEquals(actualCloud, expectedCloud)

        val actualCache = repository.fetchBooks()
        val expectedCache = BooksData.Success(listOf(
            Book(0, "name0 db"),
            Book(1, "name1 db"),
            Book(2, "name2 db"),
        ))

        assertEquals(actualCache, expectedCache)
    }

        private inner class TestBooksCloudDataSource : BooksCloudDataSource {
            override suspend fun fetchBooks(): List<BookCloud> {
                return listOf(
                    BookCloud(0, "name0"),
                    BookCloud(1, "name1"),
                    BookCloud(2, "name2"),
                )
            }
        }

        private inner class TestBooksCacheDataSource : BooksCacheDataSource {

            private val list = ArrayList<BookDB>()

            override fun fetchBooks() = list

            override fun saveBooks(books: List<Book>) {
                books.map { book ->
                    list.add(BookDB().apply {
                        this.id = book.id
                        this.name = "${book.name} db"
                    })
                }
            }
        }
    }