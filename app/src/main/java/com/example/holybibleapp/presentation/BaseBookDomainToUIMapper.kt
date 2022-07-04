package com.example.holybibleapp.presentation

import com.example.holybibleapp.R
import com.example.holybibleapp.domain.BookDomainToUIMapper
import com.example.holybibleapp.domain.TestamentType

class BaseBookDomainToUIMapper(
    private val resourceProvider: ResourceProvider
) : BookDomainToUIMapper {
    override fun map(id: Int, name: String) = when (id) {
        TestamentType.NEW.getId() -> BookUI.Testament(
            id,
            resourceProvider.getString(R.string.new_testament)
        )
        TestamentType.OLD.getId() -> BookUI.Testament(
            id,
            resourceProvider.getString(R.string.old_testament)
        )
        else -> BookUI.Base(id, name)
    }
}