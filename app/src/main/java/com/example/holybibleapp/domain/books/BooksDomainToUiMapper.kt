package com.example.holybibleapp.domain.books

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.ResourceProvider
import com.example.holybibleapp.presentation.books.BooksUi

abstract class BooksDomainToUiMapper(
    resourceProvider: ResourceProvider
) : Abstract.Mapper.DomainToUi.Base<List<BookDomain>, BooksUi>(resourceProvider)