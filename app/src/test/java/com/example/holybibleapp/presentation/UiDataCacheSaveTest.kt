package com.example.holybibleapp.presentation

import org.junit.Assert.*
import org.junit.Test

class UiDataCacheSaveTest {

    @Test
    fun test_empty_ids() {
        val idCache = TestIdCache()
        val uiDataCache = UiDataCache.Base(idCache)
        val actual = uiDataCache.cache(
            listOf(
                BookUI.Testament(0, "0"),
                BookUI.Base(1, "1"),
            )
        )
        val expected = BooksUI.Base(
            listOf(
                BookUI.Testament(0, "0"),
                BookUI.Base(1, "1"),
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
                BookUI.Testament(0, "0"),
                BookUI.Base(1, "1"),
                BookUI.Testament(2, "2"),
                BookUI.Base(3, "3"),
            )
        )
        val expected = BooksUI.Base(
            listOf(
                BookUI.Testament(0, "0", true),
                BookUI.Testament(2, "2"),
                BookUI.Base(3, "3"),
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
                BookUI.Testament(0, "0"),
                BookUI.Base(1, "1"),
                BookUI.Testament(2, "2"),
                BookUI.Base(3, "3"),
            )
        )
        val expected = BooksUI.Base(
            listOf(
                BookUI.Testament(0, "0", true),
                BookUI.Testament(2, "2", true),
            )
        )
        assertEquals(expected, actual)
    }


    private inner class TestIdCache : IdCache {

        private val set = mutableSetOf<Int>()

        override fun read() = set
        override fun save(id: Int) {
            set.add(id)
        }

        override fun finish() = Unit
        override fun start() = set.clear()
    }
}