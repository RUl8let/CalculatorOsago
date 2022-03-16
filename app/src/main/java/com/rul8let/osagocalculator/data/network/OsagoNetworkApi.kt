package com.rul8let.osagocalculator.data.network

import com.rul8let.osagocalculator.data.model.CompanyNetworkModel
import com.rul8let.osagocalculator.data.model.CoefficientNetworkModel
import retrofit2.http.GET

interface OsagoNetworkApi {
    @GET("rationDetail")
    suspend fun getCoefficientInfo(
    ): CoefficientNetworkModel

    @GET("startCalculation")
    suspend fun getCompany(): CompanyNetworkModel
}