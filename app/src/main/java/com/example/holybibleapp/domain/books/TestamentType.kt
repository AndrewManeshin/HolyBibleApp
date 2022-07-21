package com.example.holybibleapp.domain.books

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.presentation.books.BookUi

enum class TestamentType(private val id: Int) : Abstract.Object<BookUi, BookDomainToUiMapper> {
    OLD(Int.MIN_VALUE),
    NEW(Int.MAX_VALUE);

    fun matches(id: Int) = this.id == id
    override fun map(mapper: BookDomainToUiMapper) = mapper.map(id, name)
}