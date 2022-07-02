package com.example.holybibleapp.core

abstract class Abstract {

    abstract class Object<T, M : Mapper> {
        abstract fun map(mapper: M): T
    }

    //todo rename
    interface Mapable<T, M : Mapper> {
        fun map(mapper: M): T
    }

    interface Mapper {
        class Empty : Mapper
    }

}