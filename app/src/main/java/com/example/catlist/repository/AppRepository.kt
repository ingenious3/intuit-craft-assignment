package com.example.catlist.repository

import com.example.catlist.data.CatListItem
import com.example.catlist.datasource.network.ResultData

interface AppRepository {

    suspend fun getCatList(limit: Int, page: Int, order: String) : ResultData<List<CatListItem>>

}