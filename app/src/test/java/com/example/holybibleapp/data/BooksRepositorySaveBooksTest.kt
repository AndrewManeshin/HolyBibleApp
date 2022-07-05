package com.example.holybibleapp.data

import com.example.holybibleapp.data.cache.BookDB
import com.example.holybibleapp.data.cache.BooksCacheDataSource
import com.example.holybibleapp.data.cache.BooksCacheMapper
import com.example.holybibleapp.data.cache.DBWrapper
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
            BooksCloudMapper.Base(TestToBookMapper()),
            BooksCacheMapper.Base(TestToBookMapper())
        )

        val actualCloud = repository.fetchBooks()
        val expectedCloud = BooksData.Success(
            listOf(
                BookData(0, "name0"),
                BookData(1, "name1"),
                BookData(2, "name2"),
            )
        )

        assertEquals(actualCloud, expectedCloud)

        val actualCache = repository.fetchBooks()
        val expectedCache = BooksData.Success(
            listOf(
                BookData(0, "name0 db"),
                BookData(1, "name1 db"),
                BookData(2, "name2 db"),
            )
        )

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

        override fun saveBooks(books: List<BookData>) {
            books.map { book ->
                list.add(book.mapTo(object : BookDataToDBMapper {
                    override fun mapToDB(id: Int, name: String, dbWrapper: DBWrapper) =
                        BookDB().apply {
                            this.id = id
                            this.name = "$name db"
                        }
                }, object : DBWrapper {
                    override fun createObject(id: Int) = BookDB().apply {
                        this.id = id
                    }
                }))
            }
        }
    }
}