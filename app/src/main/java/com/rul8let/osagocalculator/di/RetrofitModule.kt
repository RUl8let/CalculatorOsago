package com.rul8let.osagocalculator.di

import com.rul8let.osagocalculator.data.network.OsagoNetworkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    fun retrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://mock.sravni-team.ru/mobile/internship/v1/osago/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideOsagoInfoRetrofit(retrofit: Retrofit): OsagoNetworkApi =
        retrofit.create(OsagoNetworkApi::class.java)
}