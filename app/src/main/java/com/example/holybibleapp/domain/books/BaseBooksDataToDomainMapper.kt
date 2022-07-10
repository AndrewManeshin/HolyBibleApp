package com.example.holybibleapp.domain.books

import com.example.holybibleapp.data.books.BookData
import com.example.holybibleapp.data.books.BookDataToDomainMapper
import com.example.holybibleapp.data.books.BooksDataToDomainMapper
import com.example.holybibleapp.data.books.TestamentTemp
import com.example.holybibleapp.core.ErrorType
import com.example.holybibleapp.domain.TestamentType
import retrofit2.HttpException
import java.net.UnknownHostException

class BaseBooksDataToDomainMapper(
    private val bookMapper: BookDataToDomainMapper
) : BooksDataToDomainMapper {

    override fun map(books: List<BookData>): BooksDomain {
        val data = mutableListOf<BookDomain>()
        val temp = TestamentTemp.Base()
        books.forEach { bookData ->
            if (!bookData.matches(temp)) {
                if (temp.isEmpty())
                    data.add(BookDomain.Testament(TestamentType.OLD))
                else
                    data.add(BookDomain.Testament(TestamentType.NEW))
                bookData.saveTestament(temp)
            }
            data.add(bookData.map(bookMapper))
        }
        return BooksDomain.Success(data)
    }

    override fun map(exception: Exception) = BooksDomain.Fail(
        when (exception) {
            is UnknownHostException -> ErrorType.NO_CONNECTION
            is HttpException -> ErrorType.SERVICE_UNAVAILABLE
            else -> ErrorType.GENERIC_ERROR
        }
    )
}