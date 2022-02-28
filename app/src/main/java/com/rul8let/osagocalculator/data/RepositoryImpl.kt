package com.rul8let.osagocalculator.data

import com.rul8let.osagocalculator.data.model.Root
import com.rul8let.osagocalculator.data.network.CoefficientNetworkApi
import com.rul8let.osagocalculator.ui.CoefficientEnum
import com.rul8let.osagocalculator.ui.model.CoefficientItem
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RepositoryImpl @Inject constructor (
    private val coefficientNetworkApi : CoefficientNetworkApi
        ) : Repository{

    override suspend fun getData(): Response<List<CoefficientItem>> {
        return try {
            val response = coefficientNetworkApi.getCoefficientInfo()
            Response.Success(mapCoefficient(response))
        } catch (e : IOException){
            Response.Error(e)
        } catch (e : HttpException){
            Response.Error(e)
        }
    }

    private fun mapCoefficient(response: Root): List<CoefficientItem> {
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