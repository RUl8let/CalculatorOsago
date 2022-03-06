package com.rul8let.osagocalculator.ui.model

sealed class PriceCalculation(){
    abstract fun getType() : Int

    data class PriceCalculationItem (
        val name : String,
        val rating : String,
        val price : String,
        val backgroundColor : String,
        val UrlSVG : String?) : PriceCalculation() {
        override fun getType() = data
    }

    class Load() : PriceCalculation() {
        override fun getType() = load
    }

    companion object {
        const val load = 0
        const val data = 1
    }
}