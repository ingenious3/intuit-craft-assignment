package com.example.catlist.di.component

import android.content.Context
import com.example.catlist.datasource.db.AppDb
import com.example.catlist.di.modules.*
import com.example.catlist.ui.CatListActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataSourceModule::class, DbModule::class, NetworkModule::class, ViewModelModule::class, RepositoryModule::class])
interface AppComponent {

    fun inject(activity: CatListActivity)

    fun context(): Context

}