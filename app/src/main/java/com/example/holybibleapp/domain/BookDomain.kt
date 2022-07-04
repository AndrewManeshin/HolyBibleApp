package com.example.holybibleapp.domain

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.presentation.BookUI

class BookDomain(
    private val id: Int,
    private val name: String
) : Abstract.Object<BookUI, BookDomainToUIMapper> {
    override fun map(mapper: BookDomainToUIMapper) = mapper.map(id, name)
}