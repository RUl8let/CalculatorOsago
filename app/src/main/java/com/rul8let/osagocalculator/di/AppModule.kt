package com.rul8let.osagocalculator.di

import com.rul8let.osagocalculator.data.Repository
import com.rul8let.osagocalculator.data.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {
    @Binds
    abstract fun bindCoefficient(coefficientRepository : RepositoryImpl) : Repository
}