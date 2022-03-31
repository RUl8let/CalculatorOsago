package com.rul8let.osagocalculator

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OsagoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        app = this
    }

    companion object {
        private lateinit var app : OsagoApp
        val appContext : Context get() = app.applicationContext

    }
}