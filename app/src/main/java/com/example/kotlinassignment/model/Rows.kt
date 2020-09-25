package com.example.kotlinassignment.model

import com.google.gson.annotations.SerializedName
/*data model class for api response*/
data class Rows(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("imageHref") val imageHref: String
)