package com.example.holybibleapp.domain

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.presentation.BookUI

interface BookDomainToUIMapper : Abstract.Mapper {
    fun map(id: Int, name: String) : BookUI
}