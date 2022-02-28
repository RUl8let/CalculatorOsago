package com.rul8let.osagocalculator.data.model

import com.google.gson.annotations.SerializedName

data class Factor (
    @SerializedName("title")
    val title : String,

    @SerializedName("headerValue")
    val headerValue : String,

    @SerializedName("value")
    val value : String,

    @SerializedName("name")
    val name : String,

    @SerializedName("detailText")
    val detailText : String
)