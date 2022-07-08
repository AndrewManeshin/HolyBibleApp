package com.example.holybibleapp.data.cache

import io.realm.Realm

interface DBWrapper {

    fun createObject(id: Int): BookDB

    class Base(private val realm: Realm) : DBWrapper {
        override fun createObject(id: Int): BookDB {
            return realm.createObject(BookDB::class.java, id)
        }
    }
}