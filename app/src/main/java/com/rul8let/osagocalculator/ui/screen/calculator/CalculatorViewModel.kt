package com.rul8let.osagocalculator.ui.screen.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rul8let.osagocalculator.R
import com.rul8let.osagocalculator.ui.InfoInputEnum
import com.rul8let.osagocalculator.ui.model.CoefficientItem
import com.rul8let.osagocalculator.ui.model.InputInfoItem

class CalculatorViewModel : ViewModel() {
    private val _expanded = MutableLiveData<Boolean>(false)
    val expanded : LiveData<Boolean> = _expanded

    private val _coefficientList = MutableLiveData<List<CoefficientItem>>(generationStartCoefficient())
    val coefficientList : LiveData<List<CoefficientItem>> = _coefficientList

    private val _inputInfoList = MutableLiveData<List<InputInfoItem>>(generationInputList())
    val inputInfoList : LiveData<List<InputInfoItem>> = _inputInfoList

    fun changeExpanded(b: Boolean) {
        _expanded.value = b
    }

    private fun generationStartCoefficient() : List<CoefficientItem>{
        val list = mutableListOf<CoefficientItem>()
        list.add(CoefficientItem("", R.array.BT))
        list.add(CoefficientItem("", R.array.KM))
        list.add(CoefficientItem("", R.array.KT))
        list.add(CoefficientItem("", R.array.KBM))
        list.add(CoefficientItem("", R.array.KBC))
        list.add(CoefficientItem("", R.array.KO))
        return list
    }

    private fun generationInputList() : List<InputInfoItem>{
        val list = mutableListOf<InputInfoItem>()
        InfoInputEnum.values().forEach {
            list.add( InputInfoItem(it,""))
        }
        return list
    }
}