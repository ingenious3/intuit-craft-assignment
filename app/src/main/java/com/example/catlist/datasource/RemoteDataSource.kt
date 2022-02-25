package com.example.catlist.datasource

import com.example.catlist.data.CatListItem
import com.example.catlist.datasource.network.ResultData

interface RemoteDataSource {

    suspend fun getCatList(limit: Int, page: Int, order: String) : ResultData<List<CatListItem>>
}