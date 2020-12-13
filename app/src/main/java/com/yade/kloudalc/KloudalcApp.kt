package com.yade.kloudalc

import android.app.Application
import android.content.Context

class KloudalcApp : Application() {

    val Context.kloudAlcApp: KloudalcApp
    get() = applicationContext as KloudalcApp

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: KloudalcApp
            private set
    }
}