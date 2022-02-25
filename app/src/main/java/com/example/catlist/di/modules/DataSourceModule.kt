package com.example.catlist.di.modules

import com.example.catlist.datasource.LocalDataSource
import com.example.catlist.datasource.LocalDataSourceImpl
import com.example.catlist.datasource.RemoteDataSource
import com.example.catlist.datasource.RemoteDataSourceImpl
import com.example.catlist.datasource.db.AppDao
import com.example.catlist.datasource.network.ApiInterface
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataSourceModule {

    @Singleton
    @Provides
    fun providesLocalDataSource(appDao: AppDao) : LocalDataSource {
        return LocalDataSourceImpl(appDao)
    }

    @Singleton
    @Provides
    fun providesRemoteDatasource(api: ApiInterface) : RemoteDataSource {
        return RemoteDataSourceImpl(api)
    }

}