package com.example.holybibleapp.presentation.books

import com.example.holybibleapp.R
import com.example.holybibleapp.domain.books.BookDomainToUiMapper
import com.example.holybibleapp.domain.books.TestamentType
import com.example.holybibleapp.core.ResourceProvider

class BaseBookDomainToUiMapper(
    private val resourceProvider: ResourceProvider
) : BookDomainToUiMapper {
    override fun map(id: Int, name: String) = when {
        TestamentType.NEW.matches(id) -> BookUI.Testament(
            id,
            resourceProvider.getString(R.string.new_testament)
        )
        TestamentType.OLD.matches(id) -> BookUI.Testament(
            id,
            resourceProvider.getString(R.string.old_testament)
        )
        else -> BookUI.Base(id, name)
    }
}