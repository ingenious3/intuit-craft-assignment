package com.example.catlist.repository

import com.example.catlist.App
import com.example.catlist.data.CatListItem
import com.example.catlist.datasource.LocalDataSource
import com.example.catlist.datasource.RemoteDataSource
import com.example.catlist.datasource.network.ResultData
import com.example.catlist.utils.ConnectivityUtils
import com.example.catlist.utils.CustomException

class AppRepositoryImpl (private val remoteDataSource: RemoteDataSource,
                         private val localDataSource: LocalDataSource) : AppRepository {

    private val isInternetOn = ConnectivityUtils().isInternetOn(App.component.context())

    override suspend fun getCatList(limit: Int, page: Int, order: String): ResultData<List<CatListItem>> {

        return if (isInternetOn) {
            when (val result = remoteDataSource.getCatList(limit, page, order)) {
                is ResultData.Success -> {
                    val response = result.data
                    localDataSource.setDataInDB(response)
                    val localData = localDataSource.getCatList()
                    localData
                }
                is ResultData.Error -> {
                    ResultData.Error(CustomException("Remote Error"))
                }
            }
        } else {
            when ( val localData = localDataSource.getCatList()) {
                is ResultData.Success -> {
                    if (!localData.data.isNullOrEmpty()) {
                        ResultData.Success(localData.data)
                    } else {
                        ResultData.Error(CustomException("Data Not Available"))
                    }
                }
                is ResultData.Error -> {
                    ResultData.Error(CustomException("Local DB Error"))
                }
            }
        }
    }
}