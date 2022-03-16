package com.rul8let.osagocalculator.data

import com.rul8let.osagocalculator.ui.model.CoefficientItem
import com.rul8let.osagocalculator.ui.model.CompanySealed
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun observerCoefficientData() : Flow<Response<List<CoefficientItem>>>

    suspend fun updateCoefficientData()

    suspend fun getCompaniesData() : Response<List<CompanySealed.CompanyItem>>
}