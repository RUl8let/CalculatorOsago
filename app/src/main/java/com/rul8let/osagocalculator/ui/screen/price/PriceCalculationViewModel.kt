package com.rul8let.osagocalculator.ui.screen.price

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rul8let.osagocalculator.data.Repository
import com.rul8let.osagocalculator.data.Response
import com.rul8let.osagocalculator.ui.CoefficientEnum
import com.rul8let.osagocalculator.ui.model.CoefficientItem
import com.rul8let.osagocalculator.ui.model.PriceCalculation.*
import com.rul8let.osagocalculator.ui.model.PriceCalculation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PriceCalculationViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _coefficientList = MutableLiveData<List<CoefficientItem>>()
    val coefficientList : LiveData<List<CoefficientItem>> = _coefficientList

    private val _pricePolicy = MutableLiveData<List<PriceCalculation>>(emptyList())
    val pricePolicy : LiveData<List<PriceCalculation>> = _pricePolicy

    private val _expanded = MutableLiveData<Boolean>(false)
    val expanded : LiveData<Boolean> = _expanded

    fun changeExpanded(b: Boolean) {
        _expanded.value = b
    }

    init {
        viewModelScope.launch {
            repository.observerCoefficientData().collect{
                Log.e("coefficent","test ${it}")
                when (it){
                    is Response.Success -> {
                        _coefficientList.value = it.data ?: CoefficientEnum.generationBaseList()
                        loadDataPrices()
                    }
                    else ->{
                        Log.e("Response","${it}")}
                }
            }
        }
    }

    private suspend fun loadDataPrices(){
        _pricePolicy.value = listOf(Load(), Load(), Load(), Load())
        when (val response = repository.getDataPrices()){
            is Response.Success ->{
                _pricePolicy.value = response.data ?: emptyList()
            }
            else -> {
                _pricePolicy.value = emptyList()
                Log.e("Response","${response}")}
        }
    }
}