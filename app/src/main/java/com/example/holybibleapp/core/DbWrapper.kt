package com.example.holybibleapp.core

import io.realm.Realm
import io.realm.RealmObject

interface DbWrapper<T : RealmObject> {

    fun createObject(id: Int? = null): T

    abstract class Base<T : RealmObject>(private val realm: Realm) : DbWrapper<T> {
        override fun createObject(id: Int?): T {
            return realm.createObject(dbClass(), id)
        }

        protected abstract fun dbClass(): Class<T>
    }
}