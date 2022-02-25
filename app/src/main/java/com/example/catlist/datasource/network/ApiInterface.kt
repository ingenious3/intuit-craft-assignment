package com.example.catlist.datasource.network

import com.example.catlist.data.CatListItem
import com.example.catlist.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiInterface {

    @GET("v1/images/")
    suspend fun getCatList(@Header(Constants.X_API_KEY) apiKey : String,
                           @Header(Constants.CONTENT_TYPE) contentType : String,
                           @Query("limit") limit : Int,
                           @Query("page") page : Int,
                           @Query("order") order : String) : List<CatListItem>

}