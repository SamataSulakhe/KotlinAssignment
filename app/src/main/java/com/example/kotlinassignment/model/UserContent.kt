package com.example.kotlinassignment.model

import com.google.gson.annotations.SerializedName

/*data model class for api response*/
data class UserContent(
    @SerializedName("title") val title: String,
    @SerializedName("rows") val listRows: List<Rows>
)

