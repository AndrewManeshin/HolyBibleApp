package com.example.holybibleapp.presentation.books

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.Matcher

sealed class BookUI : Abstract.Object<Unit, BookUI.StringMapper>, Matcher<Int>, Collapsing, Comparing {

    override fun map(mapper: StringMapper) = Unit
    override fun matches(arg: Int) = false

    open fun changeState(): BookUI = Empty
    open fun saveId(cacheId: IdCache) = Unit

    object Empty : BookUI()
    object Progress : BookUI()

    abstract class Info(
        protected open val id: Int, //todo use for getting chapters
        protected open val name: String
    ) : BookUI() {
        override fun map(mapper: StringMapper) = mapper.map(name)
        override fun matches(arg: Int) = arg == id
    }

    data class Base(override val id: Int, override val name: String) : Info(id, name) {
        override fun same(bookUI: BookUI) = bookUI is Base && id == bookUI.id

        override fun sameContent(bookUI: BookUI) = if (bookUI is Base) {
            name == bookUI.name
        } else false
    }

    data class Testament(
        override val id: Int,
        override val name: String,
        private val collapsed: Boolean = false
    ) : Info(id, name) {
        override fun collapseOrExpand(listener: BibleAdapter.CollapseListener) =
            listener.collapseOrExpand(id)

        override fun showCollapsed(mapper: CollapseMapper) = mapper.show(collapsed)

        override fun isCollapsed() = collapsed

        override fun changeState() = Testament(id, name, !collapsed)

        override fun same(bookUI: BookUI) = bookUI is Testament && id == bookUI.id

        override fun sameContent(bookUI: BookUI) = if (bookUI is Testament) {
            name == bookUI.name && collapsed == bookUI.collapsed
        } else false

        override fun saveId(cacheId: IdCache) = cacheId.save(id)
    }

    data class Fail(private val message: String) : BookUI() {
        override fun map(mapper: StringMapper) = mapper.map(message)

        override fun same(bookUI: BookUI) = sameContent(bookUI)

        override fun sameContent(bookUI: BookUI) = if (bookUI is Fail) {
            message == bookUI.message
        } else false
    }

    //todo improve later
    interface StringMapper : Abstract.Mapper {
        fun map(text: String)
    }

    interface CollapseMapper : Abstract.Mapper {
        fun show(collapsed: Boolean)
    }
}

interface Collapsing {
    fun collapseOrExpand(listener: BibleAdapter.CollapseListener) = Unit
    fun showCollapsed(mapper: BookUI.CollapseMapper) = Unit
    fun isCollapsed(): Boolean = false
}

interface Comparing {
    open fun same(bookUI: BookUI) = false
    open fun sameContent(bookUI: BookUI) = false
}