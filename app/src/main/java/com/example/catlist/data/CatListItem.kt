package com.example.catlist.data

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = CatListItem.TABLE_NAME, primaryKeys = ["id"])
data class CatListItem(

    @SerializedName("id")
    val id: String,

    @SerializedName("approved")
    val approved: Int?,
    @SerializedName("breed_ids")
    val breedIds: String?,
    @SerializedName("breeds")
    val breeds: List<Breed>?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("height")
    val height: Int?,
    @SerializedName("original_filename")
    val originalFilename: String?,
    @SerializedName("pending")
    val pending: Int?,
    @SerializedName("rejected")
    val rejected: Int?,
    @SerializedName("sub_id")
    val subId: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("width")
    val width: Int?
) {
    companion object {
        const val TABLE_NAME = "CatListItem"
    }
}
