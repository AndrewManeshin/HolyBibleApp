package com.example.holybibleapp.presentation

import org.junit.Assert.*
import org.junit.Test

class UiDataCacheTest {

    private val source = listOf(
        BookUI.Testament(-1, "old"),
        BookUI.Base(1, "first"),
        BookUI.Base(2, "second"),
        BookUI.Testament(0, "old"),
        BookUI.Base(3, "third"),
        BookUI.Base(4, "forth"),
    )

    @Test
    fun test_collapse_first() {
        val dataCache = UiDataCache.Base(IdCache.Empty())
        dataCache.cache(source)

        val actual = dataCache.changeState(-1)
        val expanded = listOf(
            BookUI.Testament(-1, "old", true),
            BookUI.Testament(0, "old"),
            BookUI.Base(3, "third"),
            BookUI.Base(4, "forth"),
        )
        assertEquals(expanded, actual)
    }

    @Test
    fun test_collapse_second() {
        val dataCache = UiDataCache.Base(IdCache.Empty())
        dataCache.cache(source)

        val actual = dataCache.changeState(0)
        val expanded = listOf(
            BookUI.Testament(-1, "old"),
            BookUI.Base(1, "first"),
            BookUI.Base(2, "second"),
            BookUI.Testament(0, "old", true),
        )
        assertEquals(expanded, actual)
    }

    @Test
    fun test_collapse_first_then_expand() {
        val dataCache = UiDataCache.Base(IdCache.Empty())
        dataCache.cache(source)

        var actual = dataCache.changeState(-1)
        var expanded = listOf(
            BookUI.Testament(-1, "old", true),
            BookUI.Testament(0, "old"),
            BookUI.Base(3, "third"),
            BookUI.Base(4, "forth"),
        )
        assertEquals(expanded, actual)

        actual = dataCache.changeState(-1)
        expanded = source
        assertEquals(expanded, actual)
    }

    @Test
    fun test_collapse_second_then_expand() {
        val dataCache = UiDataCache.Base(IdCache.Empty())
        dataCache.cache(source)

        var actual = dataCache.changeState(0)
        var expanded = listOf(
            BookUI.Testament(-1, "old"),
            BookUI.Base(1, "first"),
            BookUI.Base(2, "second"),
            BookUI.Testament(0, "old", true),
        )
        assertEquals(expanded, actual)

        actual = dataCache.changeState(0)
        expanded = source
        assertEquals(expanded, actual)
    }
}