package com.example.holybibleapp.presentation

import com.example.holybibleapp.domain.BookDomainToUIMapper

class BaseBookDomainToUIMapper : BookDomainToUIMapper {
    override fun map(id: Int, name: String) = BookUI.Base(id, name)
}