package com.example.holybibleapp.data.books

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.ToDbMapper
import com.example.holybibleapp.data.books.cache.BookDB
import com.example.holybibleapp.core.DbWrapper
import com.example.holybibleapp.domain.books.BookDomain

data class BookData(
    private val id: Int,
    private val name: String,
    private val testament: String
) : Abstract.Object<BookDomain, BookDataToDomainMapper>,
    ToDbMapper<BookDB, BookDataToDBMapper> {
    override fun map(mapper: BookDataToDomainMapper) = mapper.map(id, name)

    override fun mapToDb(mapper: BookDataToDBMapper, dbWrapper: DbWrapper<BookDB>) =
        mapper.mapToDB(id, name, testament, dbWrapper)

    fun matches(temp: TestamentTemp) = temp.matches(testament)

    fun saveTestament(temp: TestamentTemp) = temp.save(testament) //todo make other fun
}