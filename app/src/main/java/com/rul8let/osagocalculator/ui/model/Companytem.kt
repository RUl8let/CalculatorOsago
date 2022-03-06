package com.rul8let.osagocalculator.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class CompanySealed(){
    abstract fun getType() : Int

    @Parcelize
    data class CompanyItem (
        val name : String,
        val rating : String,
        val price : String,
        val backgroundColor : String,
        val UrlSVG : String?) : Parcelable, CompanySealed() {
        override fun getType() = data
    }

    class Load() : CompanySealed() {
        override fun getType() = load
    }

    companion object {
        const val load = 0
        const val data = 1
    }
}