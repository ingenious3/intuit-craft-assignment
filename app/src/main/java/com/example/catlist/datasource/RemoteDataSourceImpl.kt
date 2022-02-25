package com.example.catlist.datasource

import com.example.catlist.data.CatListItem
import com.example.catlist.datasource.network.ApiInterface
import com.example.catlist.datasource.network.ResultData
import com.example.catlist.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteDataSourceImpl (private val api: ApiInterface) : RemoteDataSource {

    override suspend fun getCatList(limit: Int, page: Int, order: String): ResultData<List<CatListItem>> {
        return  withContext(Dispatchers.IO) {
            val request = api.getCatList(Constants.API_KEY, Constants.CONTENT_TYPE_JSON, limit, page, order)
            ResultData.Success(request)
        }
    }
}