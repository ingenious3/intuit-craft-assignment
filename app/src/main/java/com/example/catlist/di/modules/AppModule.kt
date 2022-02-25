package com.example.catlist.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import org.jetbrains.annotations.NotNull
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    @NotNull
    fun getContext(): Context {
        return context
    }

}