package com.example.catlist.datasource.db

import androidx.room.TypeConverter
import com.example.catlist.data.Breed
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class TypeConverter {

    @TypeConverter
    fun fromString(value: String?): List<Breed>? {
        val listType: Type = object : TypeToken<List<Breed>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toString(list: List<Breed>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromBreedString(value: String?): Breed? {
        return Gson().fromJson(value, Breed::class.java)
    }

    @TypeConverter
    fun toBreedString(breed: Breed?): String? {
        val gson = Gson()
        return gson.toJson(breed)
    }

}