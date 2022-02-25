package com.example.catlist.datasource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.catlist.data.CatListItem

@Database(entities = [CatListItem::class], version = 1)
@TypeConverters(TypeConverter::class)
abstract class AppDb : RoomDatabase() {

    abstract fun appDao(): AppDao

}
