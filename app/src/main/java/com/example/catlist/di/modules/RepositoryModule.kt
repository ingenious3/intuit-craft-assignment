package com.example.catlist.di.modules

import com.example.catlist.datasource.LocalDataSource
import com.example.catlist.datasource.RemoteDataSource
import com.example.catlist.repository.AppRepository
import com.example.catlist.repository.AppRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun providesAppRepository(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource) : AppRepository {
        return AppRepositoryImpl(remoteDataSource, localDataSource)
    }
}