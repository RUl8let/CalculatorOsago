package com.rul8let.osagocalculator.data.model

import com.google.gson.annotations.SerializedName

data class Root (
    @SerializedName("factors")
    val factors: List<Factor>
        )