package com.example.catlist.di.modules

import com.example.catlist.repository.AppRepository
import com.example.catlist.viewmodel.CatViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Singleton
    @Provides
    fun providesMainViewModelFactory(appRepository: AppRepository) : CatViewModelFactory {
        return CatViewModelFactory(appRepository)
    }

}