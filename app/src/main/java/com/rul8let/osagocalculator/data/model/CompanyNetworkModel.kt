package com.rul8let.osagocalculator.data.model

import com.google.gson.annotations.SerializedName

data class CompanyNetworkModel(

    @SerializedName("offers")
    val offers : List<Offers>,

    @SerializedName("actionText")
    val actionText : String
)

data class Branding  (

    @SerializedName("fontColor")
    val fontColor : String,

    @SerializedName("backgroundColor")
    val backgroundColor : String,

    @SerializedName("iconTitle")
    val iconTitle : String,

    @SerializedName("name")
    val name : String,

    @SerializedName("bankLogoUrlPDF")
    val bankLogoUrlPDF : String,

    @SerializedName("bankLogoUrlSVG")
    val bankLogoUrlSVG : String
)

data class Offers(

    @SerializedName("name")
    val name : String,

    @SerializedName("rating")
    val rating : String,

    @SerializedName("price")
    val price : Int,

    @SerializedName("branding")
    val branding : Branding
)