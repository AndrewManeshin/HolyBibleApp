package com.example.holybibleapp.data

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.cache.BookDB
import com.example.holybibleapp.data.cache.DBWrapper

interface BookDataToDBMapper : Abstract.Mapper {
    fun mapToDB(id: Int, name: String, testament: String, dbWrapper: DBWrapper): BookDB

    class Base : BookDataToDBMapper {
        override fun mapToDB(id: Int, name: String, testament: String, dbWrapper: DBWrapper): BookDB {
            val bookDB = dbWrapper.createObject(id)
            bookDB.name = name
            bookDB.testament = testament
            return bookDB
        }
    }
}