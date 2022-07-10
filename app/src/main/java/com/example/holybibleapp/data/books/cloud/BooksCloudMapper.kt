package com.example.holybibleapp.data.books.cloud

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.books.BookData
import com.example.holybibleapp.data.books.ToBookMapper

interface BooksCloudMapper : Abstract.Mapper {

    fun map(cloudList: List<Abstract.Object<BookData, ToBookMapper>>): List<BookData>

    class Base(private val bookMapper: ToBookMapper) : BooksCloudMapper {
        override fun map(cloudList: List<Abstract.Object<BookData, ToBookMapper>>) = cloudList.map { bookCloud ->
            bookCloud.map(bookMapper)
        }
    }
}