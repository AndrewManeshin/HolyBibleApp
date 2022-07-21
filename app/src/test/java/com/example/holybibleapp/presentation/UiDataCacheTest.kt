package com.example.holybibleapp.presentation

import com.example.holybibleapp.presentation.books.BookUi
import com.example.holybibleapp.presentation.books.CollapsedIdsCache
import com.example.holybibleapp.presentation.books.UiDataCache
import org.junit.Assert.*
import org.junit.Test

class UiDataCacheTest {

    private val source = listOf(
        BookUi.Testament(-1, "old"),
        BookUi.Base(1, "first"),
        BookUi.Base(2, "second"),
        BookUi.Testament(0, "old"),
        BookUi.Base(3, "third"),
        BookUi.Base(4, "forth"),
    )

    @Test
    fun test_collapse_first() {
        val dataCache = UiDataCache.Base(CollapsedIdsCache.Empty())
        dataCache.cache(source)

        val actual = dataCache.changeState(-1)
        val expanded = listOf(
            BookUi.Testament(-1, "old", true),
            BookUi.Testament(0, "old"),
            BookUi.Base(3, "third"),
            BookUi.Base(4, "forth"),
        )
        assertEquals(expanded, actual)
    }

    @Test
    fun test_collapse_second() {
        val dataCache = UiDataCache.Base(CollapsedIdsCache.Empty())
        dataCache.cache(source)

        val actual = dataCache.changeState(0)
        val expanded = listOf(
            BookUi.Testament(-1, "old"),
            BookUi.Base(1, "first"),
            BookUi.Base(2, "second"),
            BookUi.Testament(0, "old", true),
        )
        assertEquals(expanded, actual)
    }

    @Test
    fun test_collapse_first_then_expand() {
        val dataCache = UiDataCache.Base(CollapsedIdsCache.Empty())
        dataCache.cache(source)

        var actual = dataCache.changeState(-1)
        var expanded = listOf(
            BookUi.Testament(-1, "old", true),
            BookUi.Testament(0, "old"),
            BookUi.Base(3, "third"),
            BookUi.Base(4, "forth"),
        )
        assertEquals(expanded, actual)

        actual = dataCache.changeState(-1)
        expanded = source
        assertEquals(expanded, actual)
    }

    @Test
    fun test_collapse_second_then_expand() {
        val dataCache = UiDataCache.Base(CollapsedIdsCache.Empty())
        dataCache.cache(source)

        var actual = dataCache.changeState(0)
        var expanded = listOf(
            BookUi.Testament(-1, "old"),
            BookUi.Base(1, "first"),
            BookUi.Base(2, "second"),
            BookUi.Testament(0, "old", true),
        )
        assertEquals(expanded, actual)

        actual = dataCache.changeState(0)
        expanded = source
        assertEquals(expanded, actual)
    }
}