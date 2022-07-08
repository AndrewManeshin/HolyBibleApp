package com.example.holybibleapp.core

import android.app.Application
import com.example.holybibleapp.data.BookDataToDBMapper
import com.example.holybibleapp.data.BooksRepository
import com.example.holybibleapp.data.cache.BooksCacheDataSource
import com.example.holybibleapp.data.cache.BooksCacheMapper
import com.example.holybibleapp.data.cache.RealmProvider
import com.example.holybibleapp.data.ToBookMapper
import com.example.holybibleapp.data.net.BooksCloudDataSource
import com.example.holybibleapp.data.net.BooksCloudMapper
import com.example.holybibleapp.data.net.BooksService
import com.example.holybibleapp.domain.*
import com.example.holybibleapp.presentation.*
import retrofit2.Retrofit
import com.google.gson.Gson
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


class BibleApp : Application() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        val client = OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .build()

        val service = retrofit.create(BooksService::class.java)

        val gson = Gson()

        val cloudDataSource = BooksCloudDataSource.Base(service, gson)

        val cacheDataSource =
            BooksCacheDataSource.Base(RealmProvider.Base(), BookDataToDBMapper.Base())

        val toBookMapper = ToBookMapper.Base()

        val booksCloudMapper = BooksCloudMapper.Base(toBookMapper)

        val booksCacheMapper = BooksCacheMapper.Base(toBookMapper)

        val booksRepository = BooksRepository.Base(
            cloudDataSource,
            cacheDataSource,
            booksCloudMapper,
            booksCacheMapper
        )

        val booksInteractor = BooksInteractor.Base(
            booksRepository,
            BaseBooksDataToDomainMapper(BaseBookDataToDomainMapper())
        )

        val communication = BooksCommunication.Base()

        val resourceProvider = ResourceProvider.Base(this)

        mainViewModel = MainViewModel(
            booksInteractor,
            BaseBooksDomainToUIMapper(resourceProvider, BaseBookDomainToUIMapper(resourceProvider)),
            communication,
            UiDataCache.Base(IdCache.Base(this))
        )
    }

    private companion object {
        const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }
}