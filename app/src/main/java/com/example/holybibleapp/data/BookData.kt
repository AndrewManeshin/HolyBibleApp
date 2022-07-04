package com.example.holybibleapp.data

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.cache.BookDB
import com.example.holybibleapp.domain.BookDomain
import io.realm.Realm

class BookData(
    private val id: Int,
    private val name: String
) : Abstract.Object<BookDomain, BookDataToDomainMapper>, ToBookDB<BookDB, BookDataToDBMapper>  {
    override fun map(mapper: BookDataToDomainMapper) = mapper.map(id, name)

    override fun mapTo(mapper: BookDataToDBMapper, realm: Realm) = mapper.mapToDB(id, name, realm)
}

interface ToBookDB<T, M: Abstract.Mapper> {
    fun mapTo(mapper: M, realm: Realm) : T
}