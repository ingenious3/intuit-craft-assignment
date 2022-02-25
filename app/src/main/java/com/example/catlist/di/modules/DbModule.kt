package com.example.catlist.di.modules

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.catlist.datasource.db.AppDao
import com.example.catlist.datasource.db.AppDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    @Singleton
    @Provides
    fun providesAppDb(context: Context) : AppDb {
        return Room.databaseBuilder(context.applicationContext, AppDb::class.java, "cat_db").build()
    }

    @Singleton
    @Provides
    fun providesAppDao(appDb: AppDb) : AppDao {
        return appDb.appDao()
    }

}