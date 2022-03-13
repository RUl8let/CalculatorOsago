package com.rul8let.osagocalculator.data

import com.rul8let.osagocalculator.data.model.CompanyNetworkModel
import com.rul8let.osagocalculator.data.model.CoefficientNetworkModel
import com.rul8let.osagocalculator.data.network.OsagoNetworkApi
import com.rul8let.osagocalculator.ui.CoefficientEnum
import com.rul8let.osagocalculator.ui.model.CoefficientItem
import com.rul8let.osagocalculator.ui.model.CompanySealed.CompanyItem
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

    override suspend fun getCompaniesData(): Response<List<CompanyItem>> {
        return try {
            val response = coefficientNetworkApi.getCompany()
            Response.Success(mapCompanies(response))
        } catch (e : IOException){
            Response.Error(e)
        } catch (e : HttpException){
            Response.Error(e)
        }
    }

    private fun mapCompanies(response: CompanyNetworkModel) : List<CompanyItem>{
        val list = mutableListOf<CompanyItem>()
        response.offers.forEach {
            list.add(CompanyItem(
                name = it.name,
                rating = it.rating,
                price = it.price.toString(),
                backgroundColor = it.branding.backgroundColor,
                UrlSVG = it.branding.bankLogoUrlSVG,
                fontColor = it.branding.fontColor,
                iconTitle = it.branding.iconTitle
            ))
        }
        return list
    }

    private fun mapCoefficient(response: CoefficientNetworkModel): List<CoefficientItem> {
        val list = CoefficientEnum.generationBaseList().toMutableList()
        for (id in 0 until CoefficientEnum.values().size){
            response.factors.forEach { factor->
                if (list[id].headerValue == factor.title){
                    list[id] = CoefficientItem(
                        coefficient = factor.value,
                        idArrayString = list[id].idArrayString,
                        headerValue = factor.headerValue
                    )
                }
            }
        }
        return list
    }

}