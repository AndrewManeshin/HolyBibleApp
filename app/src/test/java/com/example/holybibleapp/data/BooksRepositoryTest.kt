//package com.example.holybibleapp.data
//
//import com.example.holybibleapp.data.cache.BookDB
//import com.example.holybibleapp.data.cache.BooksCacheDataSource
//import com.example.holybibleapp.data.cache.BooksCacheMapper
//import com.example.holybibleapp.data.net.BookCloud
//import com.example.holybibleapp.data.net.BooksCloudDataSource
//import com.example.holybibleapp.data.net.BooksCloudMapper
//import junit.framework.TestCase.assertEquals
//import kotlinx.coroutines.runBlocking
//import org.junit.Test
//import java.net.UnknownHostException
//
//class BooksRepositoryTest : BaseBooksRepositoryTest() {
//
//    private val unknownHostException = UnknownHostException()
//
//    @Test
//    fun test_no_connection_no_cache() = runBlocking {
//        val testCloudDataSource = TestBooksCloudDataSource(returnSuccess = false)
//        val testCacheDataSource = TestBooksCacheDataSource(returnSuccess = false)
//        val repository = BooksRepository.Base(
//            testCloudDataSource,
//            testCacheDataSource,
//            BooksCloudMapper.Base(TestBookMapper()),
//            BooksCacheMapper.Base(TestBookCacheMapper())
//        )
//
//        val actual = repository.fetchBooks()
//        val expected = BooksData.Fail(unknownHostException)
//
//        assertEquals(expected, actual)
//
//    }
//
//    @Test
//    fun test_cloud_success_no_cache() = runBlocking {
//        val testCloudDataSource = TestBooksCloudDataSource(returnSuccess = true)
//        val testCacheDataSource = TestBooksCacheDataSource(returnSuccess = false)
//        val repository = BooksRepository.Base(
//            testCloudDataSource,
//            testCacheDataSource,
//            BooksCloudMapper.Base(TestBookMapper()),
//            BooksCacheMapper.Base(TestBookCacheMapper())
//        )
//
//        val actual = repository.fetchBooks()
//        val expected = BooksData.Success(
//            listOf(
//                Book(0, "name0"),
//                Book(1, "name1"),
//                Book(2, "name2"),
//            )
//        )
//
//        assertEquals(expected, actual)
//    }
//
//    @Test
//    fun test_no_connection_with_cache() = runBlocking {
//        val testCloudDataSource = TestBooksCloudDataSource(returnSuccess = false)
//        val testCacheDataSource = TestBooksCacheDataSource(returnSuccess = true)
//        val repository = BooksRepository.Base(
//            testCloudDataSource,
//            testCacheDataSource,
//            BooksCloudMapper.Base(TestBookMapper()),
//            BooksCacheMapper.Base(TestBookCacheMapper())
//        )
//
//        val actual = repository.fetchBooks()
//        val expected = BooksData.Success(
//            listOf(
//                Book(10, "name10"),
//                Book(11, "name11"),
//                Book(12, "name12"),
//            )
//        )
//
//        assertEquals(expected, actual)
//    }
//
//    @Test
//    fun test_cloud_success_with_cache() = runBlocking {
//        val testCloudDataSource = TestBooksCloudDataSource(returnSuccess = true)
//        val testCacheDataSource = TestBooksCacheDataSource(returnSuccess = true)
//        val repository = BooksRepository.Base(
//            testCloudDataSource,
//            testCacheDataSource,
//            BooksCloudMapper.Base(TestBookMapper()),
//            BooksCacheMapper.Base(TestBookCacheMapper())
//        )
//
//        val actual = repository.fetchBooks()
//        val expected = BooksData.Success(
//            listOf(
//                Book(10, "name10"),
//                Book(11, "name11"),
//                Book(12, "name12"),
//            )
//        )
//
//        assertEquals(expected, actual)
//    }
//
//    private inner class TestBooksCloudDataSource(
//        private val returnSuccess: Boolean,
//    ) : BooksCloudDataSource {
//        override suspend fun fetchBooks(): List<BookCloud> {
//            if (returnSuccess) {
//                return listOf(
//                    BookCloud(0, "name0"),
//                    BookCloud(1, "name1"),
//                    BookCloud(2, "name2"),
//                )
//            } else {
//                throw unknownHostException
//            }
//        }
//    }
//
//    private inner class TestBooksCacheDataSource(
//        private val returnSuccess: Boolean,
//    ) : BooksCacheDataSource {
//
//        override fun fetchBooks(): List<BookDB> {
//            return if (returnSuccess) {
//                listOf(
//                    BookDB().apply {
//                        id = 10
//                        name = "name10"
//                    },
//                    BookDB().apply {
//                        id = 11
//                        name = "name11"
//                    },
//                    BookDB().apply {
//                        id = 12
//                        name = "name12"
//                    }
//                )
//            } else {
//                emptyList()
//            }
//        }
//
//        override fun saveBooks(books: List<Book>) {
//            // not used here
//        }
//    }
//}