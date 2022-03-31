package com.rul8let.osagocalculator.ui

import com.rul8let.osagocalculator.OsagoApp
import com.rul8let.osagocalculator.R
import com.rul8let.osagocalculator.di.RetrofitModule
import com.rul8let.osagocalculator.ui.model.CoefficientItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

enum class CoefficientEnum (val textArrayId : Int){
    BT(R.array.BT),
    KM(R.array.KM),
    KT(R.array.KT),
    KBM(R.array.KBM),
    KO(R.array.KO),
    KBC(R.array.KBC);

    companion object {
        fun generationBaseList(): List<CoefficientItem> {
            val list = mutableListOf<CoefficientItem>()
            values().forEach {
                list.add(
                    CoefficientItem(
                        coefficient = "",
                        idArrayString = it.textArrayId,
                        headerValue = OsagoApp.appContext.resources.getStringArray(it.textArrayId)[0]
                    )
                )
            }
            return list
        }
    }
}