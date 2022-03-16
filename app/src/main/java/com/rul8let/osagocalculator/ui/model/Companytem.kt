package com.rul8let.osagocalculator.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class CompanySealed{

    @Parcelize
    data class CompanyItem (
        val name : String,
        val rating : String,
        val price : String,
        val backgroundColor : String,
        val fontColor : String,
        val iconTitle : String,
        val UrlSVG : String?) : Parcelable, CompanySealed()

    object Load : CompanySealed()
}