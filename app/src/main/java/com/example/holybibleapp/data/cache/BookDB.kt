package com.example.holybibleapp.data.cache

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.Book
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class BookDB : RealmObject, Abstract.Object<Book, BookCacheMapper>() {
    @PrimaryKey
    var id: Int = -1;
    var name: String = ""

    override fun map(mapper: BookCacheMapper) = Book(id, name)
}