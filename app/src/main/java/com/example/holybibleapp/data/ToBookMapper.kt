package com.example.holybibleapp.data

import com.example.holybibleapp.core.Abstract

interface ToBookMapper : Abstract.Mapper {

    fun map(id: Int, name: String, testament: String): BookData

    class Base : ToBookMapper {
        override fun map(id: Int, name: String, testament: String) = BookData(id, name, testament)
    }
}
