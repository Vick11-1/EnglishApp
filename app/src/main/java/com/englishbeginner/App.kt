package com.englishbeginner

import android.app.Application
import com.englishbeginner.data.DatabaseHelper

class App : Application() {
    companion object {
        lateinit var dbHelper: DatabaseHelper
            private set
    }

    override fun onCreate() {
        super.onCreate()
        dbHelper = DatabaseHelper(this)
    }
}
