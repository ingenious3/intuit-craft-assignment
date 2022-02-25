package com.example.catlist.datasource

import com.example.catlist.data.CatListItem
import com.example.catlist.datasource.network.ResultData

interface LocalDataSource  {

    suspend fun getCatList() : ResultData<List<CatListItem>>

    suspend fun setDataInDB(catList: List<CatListItem>)

}