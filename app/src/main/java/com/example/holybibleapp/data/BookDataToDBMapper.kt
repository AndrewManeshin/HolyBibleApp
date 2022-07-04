package com.example.holybibleapp.data

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.cache.BookDB
import io.realm.Realm

interface BookDataToDBMapper : Abstract.Mapper {
    fun mapToDB(id: Int, name: String, testament: String, realm: Realm): BookDB

    class Base : BookDataToDBMapper {
        override fun mapToDB(id: Int, name: String, testament: String, realm: Realm): BookDB {
            val bookDB = realm.createObject(BookDB::class.java, id)
            bookDB.name = name
            bookDB.testament = testament
            return bookDB
        }
    }
}