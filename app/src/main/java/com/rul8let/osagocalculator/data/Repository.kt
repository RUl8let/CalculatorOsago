package com.rul8let.osagocalculator.data

import com.rul8let.osagocalculator.ui.model.CoefficientItem

interface Repository {
    suspend fun getData() : Response<List<CoefficientItem>>
}