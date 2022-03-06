package com.rul8let.osagocalculator.data

import com.rul8let.osagocalculator.data.model.PriceNetworkModel
import com.rul8let.osagocalculator.data.model.CoefficientNetworkModel
import com.rul8let.osagocalculator.data.network.OsagoNetworkApi
import com.rul8let.osagocalculator.ui.CoefficientEnum
import com.rul8let.osagocalculator.ui.model.CoefficientItem
import com.rul8let.osagocalculator.ui.model.PriceCalculation.PriceCalculationItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor (
    private val coefficientNetworkApi : OsagoNetworkApi
        ) : Repository{

    private val coefficientData = MutableStateFlow <Response<List<CoefficientItem>>>(Response.Success(CoefficientEnum.generationBaseList()))

    override fun observerCoefficientData() : Flow<Response<List<CoefficientItem>>> = coefficientData

    override suspend fun updateCoefficientData() {
        try {
            val response = coefficientNetworkApi.getCoefficientInfo()
            coefficientData.emit(Response.Success(mapCoefficient(response)))
        } catch (e : IOException){
            coefficientData.emit(Response.Error(e))
        } catch (e : HttpException){
            coefficientData.emit(Response.Error(e))
        }
    }

    override suspend fun getDataPrices(): Response<List<PriceCalculationItem>> {
        return try {
            val response = coefficientNetworkApi.getPolicyPrices()
            Response.Success(mapPrices(response))
        } catch (e : IOException){
            Response.Error(e)
        } catch (e : HttpException){
            Response.Error(e)
        }
    }

    private fun mapPrices(response: PriceNetworkModel) : List<PriceCalculationItem>{
        val list = mutableListOf<PriceCalculationItem>()
        response.offers.forEach {
            list.add(PriceCalculationItem(
                name = it.name,
                rating = it.rating,
                price = it.price.toString(),
                backgroundColor = it.branding.backgroundColor,
                UrlSVG = it.branding.bankLogoUrlSVG
            ))
        }
        return list
    }

    private fun mapCoefficient(response: CoefficientNetworkModel): List<CoefficientItem> {
        val list = mutableListOf<CoefficientItem>()
        CoefficientEnum.values().forEach { enum ->
            response.factors.forEach { factor->
                if (enum.title == factor.title){
                    list.add(CoefficientItem(
                        coefficient = factor.value,
                        idArrayString = enum.textArrayId,
                        headerValue = factor.headerValue
                    ))
                }
            }
        }
        return list
    }

}