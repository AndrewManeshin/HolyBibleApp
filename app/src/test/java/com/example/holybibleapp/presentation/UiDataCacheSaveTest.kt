package com.example.holybibleapp.presentation

import com.example.holybibleapp.presentation.books.BookUi
import com.example.holybibleapp.presentation.books.BooksUi
import com.example.holybibleapp.presentation.books.CollapsedIdsCache
import com.example.holybibleapp.presentation.books.UiDataCache
import org.junit.Assert.*
import org.junit.Test

class UiDataCacheSaveTest {

    @Test
    fun test_empty_ids() {
        val idCache = TestIdCache()
        val uiDataCache = UiDataCache.Base(idCache)
        val actual = uiDataCache.cache(
            listOf(
                BookUi.Testament(0, "0"),
                BookUi.Base(1, "1"),
            )
        )
        val expected = BooksUi.Base(
            listOf(
                BookUi.Testament(0, "0"),
                BookUi.Base(1, "1"),
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_not_empty_ids() {
        val idCache = TestIdCache()
        idCache.save(0)
        val uiDataCache = UiDataCache.Base(idCache)
        val actual = uiDataCache.cache(
            listOf(
                BookUi.Testament(0, "0"),
                BookUi.Base(1, "1"),
                BookUi.Testament(2, "2"),
                BookUi.Base(3, "3"),
            )
        )
        val expected = BooksUi.Base(
            listOf(
                BookUi.Testament(0, "0", true),
                BookUi.Testament(2, "2"),
                BookUi.Base(3, "3"),
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_not_empty_two_ids() {
        val idCache = TestIdCache()
        idCache.save(0)
        idCache.save(2)
        val uiDataCache = UiDataCache.Base(idCache)
        val actual = uiDataCache.cache(
            listOf(
                BookUi.Testament(0, "0"),
                BookUi.Base(1, "1"),
                BookUi.Testament(2, "2"),
                BookUi.Base(3, "3"),
            )
        )
        val expected = BooksUi.Base(
            listOf(
                BookUi.Testament(0, "0", true),
                BookUi.Testament(2, "2", true),
            )
        )
        assertEquals(expected, actual)
    }


    private inner class TestIdCache : CollapsedIdsCache {

        private val set = mutableSetOf<Int>()

        override fun read() = set
        override fun save(data: Int) {
            set.add(data)
        }

        override fun finish() = Unit
        override fun start() = set.clear()
    }
}