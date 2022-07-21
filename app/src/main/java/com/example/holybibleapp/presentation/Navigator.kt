package com.example.holybibleapp.presentation

import android.content.Context
import com.example.holybibleapp.core.Read
import com.example.holybibleapp.core.Save

interface Navigator : Save<Int>, Read<Int> {

    class Base(context: Context) : Navigator {

        private val sharedPreferences =
            context.getSharedPreferences(NAVIGATOR_FILE_NAME, Context.MODE_PRIVATE)

        override fun read() =
            sharedPreferences.getInt(CURRENT_SCREEN_KEY, 0)

        override fun save(data: Int) =
            sharedPreferences.edit().putInt(CURRENT_SCREEN_KEY, data).apply()

        private companion object {
            const val NAVIGATOR_FILE_NAME = "navigation"
            const val CURRENT_SCREEN_KEY = "screenId"
        }
    }
}

class Screens {
    companion object { //todo make private
        const val BOOKS_SCREEN = 0
        const val CHAPTERS_SCREEN = 1
    }
}