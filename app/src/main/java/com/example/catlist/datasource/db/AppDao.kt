package com.example.catlist.datasource.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.catlist.data.CatListItem
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface AppDao {

    @Query("SELECT * FROM ${CatListItem.TABLE_NAME}")
    suspend fun getDataFromDb(): List<CatListItem>

    @Insert(onConflict = REPLACE)
    suspend fun setDataInDb(catList: List<CatListItem>)

    @Query("DELETE FROM ${CatListItem.TABLE_NAME}")
    fun deleteAll()

}