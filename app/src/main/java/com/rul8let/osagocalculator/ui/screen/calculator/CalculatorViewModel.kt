package com.rul8let.osagocalculator.ui.screen.calculator

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rul8let.osagocalculator.data.Repository
import com.rul8let.osagocalculator.data.Response
import com.rul8let.osagocalculator.ui.CoefficientEnum
import com.rul8let.osagocalculator.ui.InfoInputEnum
import com.rul8let.osagocalculator.ui.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val infoInputEnum = InfoInputEnum.values()

    private val _expanded = MutableLiveData<Boolean>(false)
    val expanded : LiveData<Boolean> = _expanded

    private val _coefficientList = MutableLiveData<List<CoefficientItem>>()
    val coefficientList : LiveData<List<CoefficientItem>> = _coefficientList

    private val _inputInfoList = MutableLiveData<List<InputInfoItem>>(InfoInputEnum.generationBaseList())
    val inputInfoList : LiveData<List<InputInfoItem>> = _inputInfoList

    private var _selectInput = MutableLiveData<InfoInputEnum>(InfoInputEnum.CAR_POWER)
    val selectInput : LiveData<InfoInputEnum> = _selectInput

    private val _enabledButton = MutableLiveData<Boolean>(false)
    val enabledButton : LiveData<Boolean> = _enabledButton

    /*
     Определяет ожидаются данные из интернета или базовые.
     false = базовые данные.
     true = данные из интернета.
    */
    private var waitingBaseCoefficient = false

    init {
        viewModelScope.launch {
            repository.observerCoefficientData().collect{
                when (it){
                    is Response.Success -> {
                        _coefficientList.value = it.data ?: CoefficientEnum.generationBaseList()
                        if(waitingBaseCoefficient) _enabledButton.value = true
                    }
                    else ->{
                        waitingBaseCoefficient = false
                        Log.e("Response","$it")}
                }
            }
        }
    }

    fun changeExpanded(b: Boolean) {
        _expanded.value = b
    }

    fun selectInputUpdate (type : InfoInputEnum){
        _selectInput.value = type
    }

    fun nextInputType() {
        val type = selectInput.value?.ordinal ?: 0
        var nextType = type+1
        if (nextType==InfoInputEnum.DRIVER_AGE.ordinal && checkNumberDrivers()) {
            nextType++
        }
        _selectInput.value = infoInputEnum[nextType]
    }

    fun backInputType() {
        val type = selectInput.value?.ordinal ?: 1
        var backType = type-1
        if (backType==InfoInputEnum.DRIVER_AGE.ordinal && checkNumberDrivers()) {
            backType--
        }
        _selectInput.value = infoInputEnum[backType]
    }

    fun updateInputText(text: String) {
        val list = (inputInfoList.value ?: InfoInputEnum.generationBaseList()).toMutableList()
        val selectInputType = selectInput.value ?: InfoInputEnum.DRIVER_AGE
        list[selectInputType.ordinal] = InputInfoItem(selectInputType,text)
        _inputInfoList.value = list
        chekUpdateCoefficient()
    }

    private fun chekUpdateCoefficient(){
        var index = 0
        inputInfoList.value?.forEach {
            if (it.texts.isNotEmpty()) index++
        }

        if (checkNumberDrivers()) index++

        if (index==infoInputEnum.size){
            loadData()
        }
    }

    private fun loadData(){
        viewModelScope.launch {
            waitingBaseCoefficient = true
            repository.updateCoefficientData()
        }
    }

    private fun checkNumberDrivers()=inputInfoList.value!![InfoInputEnum.NUMBER_DRIVERS.ordinal].texts == "0"
}