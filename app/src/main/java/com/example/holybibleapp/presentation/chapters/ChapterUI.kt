package com.example.holybibleapp.presentation.chapters

import com.example.holybibleapp.core.Abstract

class ChapterUI : Abstract.Object<Unit, ChapterUI.StringMapper> {

    override fun map(mapper: StringMapper) = Unit

    //todo improve later
    interface StringMapper : Abstract.Mapper {
        fun map(text: String)
    }
}