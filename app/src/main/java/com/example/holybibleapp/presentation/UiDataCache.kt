package com.example.holybibleapp.presentation

interface UiDataCache {

    fun cache(list: List<BookUI>): BooksUI
    fun changeState(id: Int): List<BookUI>
    fun saveState()

    class Base(private val cacheId: IdCache) : UiDataCache {
        private val cachedList = ArrayList<BookUI>()

        override fun cache(list: List<BookUI>): BooksUI {
            cachedList.clear()
            cachedList.addAll(list)
            var newList = list.toList()
            val ids = cacheId.read()
            ids.forEach { id ->
                    newList = changeState(id)
                }
            return BooksUI.Base(newList)
        }

        override fun changeState(id: Int): List<BookUI> {
            val newList = ArrayList<BookUI>()
            val item = cachedList.find {
                it.matches(id)
            }

            var add = false
            cachedList.forEachIndexed { index, book ->
                if (book is BookUI.Testament) {
                    if (item == book) {
                        val element = book.changeState()
                        cachedList[index] = element
                        newList.add(element)
                        add = !element.isCollapsed()
                    } else {
                        newList.add(book)
                        add = !book.isCollapsed()
                    }
                } else {
                    if (add) newList.add(book)
                }
            }
            return newList
        }

        override fun saveState() {
            cacheId.start()
            cachedList.filter {
                it.isCollapsed()
            }.forEach {
                it.saveId(cacheId)
            }
            cacheId.finish()
        }
    }
}