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
import com.rul8let.osagocalculator.ui.model.CoefficientItem
import com.rul8let.osagocalculator.ui.model.InputInfoItem
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

    private val _coefficientList = MutableLiveData<List<CoefficientItem>>(generationStartCoefficient())
    val coefficientList : LiveData<List<CoefficientItem>> = _coefficientList

    private val _inputInfoList = MutableLiveData<List<InputInfoItem>>(generationInputList())
    val inputInfoList : LiveData<List<InputInfoItem>> = _inputInfoList

    private var _selectInput = MutableLiveData<Enum<InfoInputEnum>>(InfoInputEnum.CAR_POWER)
    val selectInput : LiveData<Enum<InfoInputEnum>> = _selectInput

    private val _enabledButton = MutableLiveData<Boolean>(false)
    val enabledButton : LiveData<Boolean> = _enabledButton

    fun changeExpanded(b: Boolean) {
        _expanded.value = b
    }

    private fun generationInputList() : List<InputInfoItem>{
        val list = mutableListOf<InputInfoItem>()
        infoInputEnum.forEach {
            list.add( InputInfoItem(it,""))
        }
        return list
    }

    fun selectInputUpdate (type : InfoInputEnum){
        _selectInput.value = type
    }

    fun nextInputType() {
        val type = selectInput.value?.ordinal ?: 1
        var nextType = type+1
        if (nextType==InfoInputEnum.DRIVER_AGE.ordinal &&
            inputInfoList.value!![InfoInputEnum.NUMBER_DRIVERS.ordinal].texts == "0") {
            nextType++
        }
        _selectInput.value = infoInputEnum[nextType]
    }

    fun backInputType() {
        val type = selectInput.value?.ordinal ?: 2
        var backType = type-1
        if (backType==InfoInputEnum.DRIVER_AGE.ordinal &&
            inputInfoList.value!![InfoInputEnum.NUMBER_DRIVERS.ordinal].texts == "0") {
            backType--
        }
        _selectInput.value = infoInputEnum[backType]
    }

    fun updateInputText(text: String) {
        val list = inputInfoList.value!!.toMutableList()
        val selectInputType = selectInput.value
        list[selectInputType!!.ordinal] = InputInfoItem(selectInputType as InfoInputEnum,text)
        _inputInfoList.value = list
        getCoefficient()
    }

    private fun getCoefficient(){
        var index = 0
        inputInfoList.value!!.forEach {
            if (it.texts.isNotEmpty()) index++
        }

        if (inputInfoList.value!![InfoInputEnum.NUMBER_DRIVERS.ordinal].texts == "0"){
            index++
        }

        if (index==infoInputEnum.size){
            loadData()
        }
    }

    private fun generationStartCoefficient() : List<CoefficientItem>{
        val list = mutableListOf<CoefficientItem>()
        CoefficientEnum.values().forEach {
            list.add(
                CoefficientItem(
                    coefficient = "",
                    idArrayString = it.textArrayId,
                    headerValue = it.title
                )
            )
        }
        return list
    }

    private fun loadData(){
        viewModelScope.launch {
            when (val response = repository.getData()){
                is Response.Success -> {
                    _coefficientList.value = response.data!!
                    _enabledButton.value = true
                }
                else ->{
                    Log.e("Response","${response}")}
            }
        }
    }
}