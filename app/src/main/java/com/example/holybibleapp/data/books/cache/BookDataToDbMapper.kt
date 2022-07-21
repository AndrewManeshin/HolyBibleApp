package com.example.holybibleapp.data.books.cache

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.DbWrapper

interface BookDataToDbMapper : Abstract.Mapper {

    fun mapToDb(id: Int, name: String, testament: String, dbWrapper: DbWrapper<BookDb>): BookDb

    class Base : BookDataToDbMapper {
        override fun mapToDb(id: Int, name: String, testament: String, dbWrapper: DbWrapper<BookDb>): BookDb {
            val bookDB = dbWrapper.createObject(id)
            bookDB.name = name
            bookDB.testament = testament
            return bookDB
        }
    }
}