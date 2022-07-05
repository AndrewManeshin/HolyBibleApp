package com.example.holybibleapp.data

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.cache.BookDB
import com.example.holybibleapp.data.cache.DBWrapper
import com.example.holybibleapp.domain.BookDomain

data class BookData(
    private val id: Int,
    private val name: String,
    private val testament: String
) : Abstract.Object<BookDomain, BookDataToDomainMapper>, ToBookDB<BookDB, BookDataToDBMapper> {
    override fun map(mapper: BookDataToDomainMapper) = mapper.map(id, name)

    override fun mapTo(mapper: BookDataToDBMapper, dbWrapper: DBWrapper) =
        mapper.mapToDB(id, name, testament, dbWrapper)

    fun compare(temp: TestamentTemp) = temp.matches(testament)

    fun saveTestament(temp: TestamentTemp) = temp.save(testament) //todo make other fun
}

interface TestamentTemp {
    fun save(testament: String)

    fun matches(testament: String) : Boolean

    fun isEmpty() : Boolean

    class Base : TestamentTemp {
        private var temp: String = ""

        override fun save(testament: String) {
            temp = testament
        }

        override fun matches(testament: String) = temp == testament

        override fun isEmpty() = temp.isEmpty()
    }
}

interface ToBookDB<T, M: Abstract.Mapper> {
    fun mapTo(mapper: M, dbWrapper: DBWrapper) : T
}