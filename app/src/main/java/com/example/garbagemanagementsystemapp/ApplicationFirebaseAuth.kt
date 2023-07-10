package com.example.garbagemanagementsystemapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationFirebaseAuth : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
    }

companion object{
        lateinit var context: ApplicationFirebaseAuth
}

}