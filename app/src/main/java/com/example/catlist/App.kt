package com.example.catlist

import android.app.Application
import com.example.catlist.di.component.AppComponent
import com.example.catlist.di.component.DaggerAppComponent
import com.example.catlist.di.modules.AppModule
import com.example.catlist.di.modules.DbModule

class App : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}