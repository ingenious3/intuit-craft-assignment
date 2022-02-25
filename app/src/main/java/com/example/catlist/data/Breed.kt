package com.example.catlist.data

import com.google.gson.annotations.SerializedName

data class Breed(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("wikipedia_url")
    val wikipediaUrl: String
)