package com.example.holybibleapp.domain.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.presentation.chapters.ChapterUI

interface ChapterDomainToUIMapper : Abstract.Mapper {
    fun map(id: Int, bookId: Int) : ChapterUI
}