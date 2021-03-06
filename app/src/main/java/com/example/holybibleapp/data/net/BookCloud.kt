package com.example.holybibleapp.data.net

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.BookData
import com.example.holybibleapp.data.ToBookMapper
import com.google.gson.annotations.SerializedName;

/**
 *{"id":1,"name":"Genesis","testament":"OT","genre":{"id":1,"name":"Law"}}
 */

data class BookCloud(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("name")
    private val name: String,
    @SerializedName("testament")
    private val testament: String
) : Abstract.Object<BookData, ToBookMapper> {
    override fun map(mapper: ToBookMapper) = mapper.map(id, name, testament)
}
