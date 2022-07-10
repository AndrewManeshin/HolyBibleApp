package com.example.holybibleapp.data.books

interface TestamentTemp {
    fun save(testament: String)

    fun matches(testament: String) : Boolean

    fun isEmpty() : Boolean

    class Base : TestamentTemp {
        private var temp: String = ""

        override fun save(testament: String) {
            temp = testament
        }

        override fun matches(testament: String) = temp == testament

        override fun isEmpty() = temp.isEmpty()
    }
}