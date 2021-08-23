package com.example.clearscore

import android.app.Application
import com.example.clearscore.di.DaggerAppComponent

class App: Application() {
    val appComponent = DaggerAppComponent.create()
}