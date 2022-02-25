package com.example.catlist.datasource

import com.example.catlist.data.CatListItem
import com.example.catlist.datasource.db.AppDao
import com.example.catlist.datasource.network.ResultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataSourceImpl (private val appDao: AppDao) : LocalDataSource {

    override suspend fun getCatList(): ResultData<List<CatListItem>> {
        return  withContext(Dispatchers.IO) {
            val dataFromDB = appDao.getDataFromDb()
            ResultData.Success(dataFromDB)
        }
    }

    override suspend fun setDataInDB(catList: List<CatListItem>) {
        withContext(Dispatchers.IO) {
           appDao.setDataInDb(catList)
        }
    }


}