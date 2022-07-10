package com.example.holybibleapp.domain.books

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.domain.TestamentType
import com.example.holybibleapp.presentation.books.BookUI

sealed class BookDomain : Abstract.Object<BookUI, BookDomainToUIMapper> {

    data class Base(
        private val id: Int,
        private val name: String
    ) : BookDomain() {
        override fun map(mapper: BookDomainToUIMapper) = mapper.map(id, name)
    }

    data class Testament(private val type: TestamentType) : BookDomain() {
        override fun map(mapper: BookDomainToUIMapper) = type.map(mapper)
    }
}