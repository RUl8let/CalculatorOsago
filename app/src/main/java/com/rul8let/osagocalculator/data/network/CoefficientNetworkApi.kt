package com.rul8let.osagocalculator.data.network

import com.rul8let.osagocalculator.data.model.Root
import retrofit2.http.GET

interface CoefficientNetworkApi {
    @GET("rationDetail")
    suspend fun getCoefficientInfo(
    ): Root
}