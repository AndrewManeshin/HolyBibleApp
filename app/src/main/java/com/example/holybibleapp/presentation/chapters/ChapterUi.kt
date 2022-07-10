package com.example.holybibleapp.presentation.chapters

import com.example.holybibleapp.core.ComparableTextMapper
import com.example.holybibleapp.core.TextMapper

sealed class ChapterUi : ComparableTextMapper<ChapterUi> {

    class Base(
        private val id: Int,
        private val text: String
    ) : ChapterUi() {
        override fun map(mapper: TextMapper) {
            TODO("Not yet implemented")
        }
    }
}