package com.example.holybibleapp.data.cache

import io.realm.kotlin.RealmConfiguration

interface RealmProvider {

    fun provide(): RealmConfiguration

    class Base : RealmProvider {

        override fun provide(): RealmConfiguration {
            return RealmConfiguration.create(schema = setOf(BookDB::class))
        }
    }
}