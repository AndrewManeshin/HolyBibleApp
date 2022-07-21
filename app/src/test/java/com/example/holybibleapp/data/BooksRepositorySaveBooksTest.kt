package com.example.holybibleapp.data

import com.example.holybibleapp.data.books.BookData
import com.example.holybibleapp.data.books.BooksData
import com.example.holybibleapp.data.books.BooksRepository
import com.example.holybibleapp.data.books.cache.BookDb
import com.example.holybibleapp.data.books.cache.BooksCacheDataSource
import com.example.holybibleapp.data.books.cache.BooksCacheMapper
import com.example.holybibleapp.core.DbWrapper
import com.example.holybibleapp.data.books.cache.BookDataToDbMapper
import com.example.holybibleapp.data.books.cloud.BookCloud
import com.example.holybibleapp.data.books.cloud.BooksCloudDataSource
import com.example.holybibleapp.data.books.cloud.BooksCloudMapper
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
                BookData(0, "name0", "ot"),
                BookData(1, "name1", "ot"),
                BookData(2, "name2", "nt"),
            )
        )

        assertEquals(actualCloud, expectedCloud)

        val actualCache = repository.fetchBooks()
        val expectedCache = BooksData.Success(
            listOf(
                BookData(0, "name0 db", "ot db"),
                BookData(1, "name1 db", "ot db"),
                BookData(2, "name2 db","nt db"),
            )
        )

        assertEquals(actualCache, expectedCache)
    }

    private inner class TestBooksCloudDataSource : BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> {
            return listOf(
                BookCloud(0, "name0","ot"),
                BookCloud(1, "name1","ot"),
                BookCloud(2, "name2","nt"),
            )
        }
    }

    private inner class TestBooksCacheDataSource : BooksCacheDataSource {

        private val list = ArrayList<BookDb>()

        override fun read() = list

        override fun save(books: List<BookData>) {
            books.map { book ->
                list.add(book.mapBy(object : BookDataToDbMapper {
                    override fun mapToDb(id: Int, name: String, testament: String, dbWrapper: DbWrapper<BookDb>) =
                        BookDb().apply {
                            this.id = id
                            this.name = "$name db"
                            this.testament = "$testament db"
                        }
                }, object : DbWrapper<BookDb> {
                    override fun createObject(id: Int) = BookDb().apply {
                        this.id = id
                    }
                }))
            }
        }
    }
}