package com.example.holybibleapp.presentation.books

import com.example.holybibleapp.core.Abstract

sealed class BookUI : Abstract.Object<Unit, BookUI.StringMapper> {

    override fun map(mapper: StringMapper) = Unit

    object Progress : BookUI()

    abstract class Info(
        private val id: Int, //todo use for getting chapters
        private val name: String
    ) : BookUI() {
        override fun map(mapper: StringMapper) = mapper.map(name)
    }

    open class Base(
        id: Int,
        name: String
    ) : Info(id, name)

    open class Testament(
        id: Int,
        name: String
    ) : Info(id, name)

    data class Fail(
        private val massage: String
    ) : BookUI() {
        override fun map(mapper: StringMapper) = mapper.map(massage)
    }

    //todo improve later
    interface StringMapper : Abstract.Mapper {
        fun map(text: String)
    }
}