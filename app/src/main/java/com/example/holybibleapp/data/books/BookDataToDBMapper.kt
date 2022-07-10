package com.example.holybibleapp.data.books

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.books.cache.BookDB
import com.example.holybibleapp.core.DbWrapper

interface BookDataToDBMapper : Abstract.Mapper {
    fun mapToDB(id: Int, name: String, testament: String, dbWrapper: DbWrapper<BookDB>): BookDB

    class Base : BookDataToDBMapper {
        override fun mapToDB(id: Int, name: String, testament: String, dbWrapper: DbWrapper<BookDB>): BookDB {
            val bookDB = dbWrapper.createObject(id)
            bookDB.name = name
            bookDB.testament = testament
            return bookDB
        }
    }
}