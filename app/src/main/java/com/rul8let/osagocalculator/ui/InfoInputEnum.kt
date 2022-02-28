package com.rul8let.osagocalculator.ui

import android.text.InputType
import com.rul8let.osagocalculator.R

enum class InfoInputEnum (val stringResId : Int, val inputType : Int){
    CITY(R.string.city, InputType.TYPE_CLASS_TEXT),
    CAR_POWER(R.string.car_power, InputType.TYPE_CLASS_NUMBER),
    NUMBER_DRIVERS(R.string.number_drivers, InputType.TYPE_CLASS_NUMBER),
    DRIVER_AGE(R.string.driver_age, InputType.TYPE_CLASS_NUMBER),
    MIN_EXPERIENCE(R.string.min_experience, InputType.TYPE_CLASS_TEXT),
    YEAR_ACCIDENT(R.string.year_accident, InputType.TYPE_CLASS_TEXT)
}