package com.padcmyanmar.mjnm.themovieapp.persistance.typeconverters

import androidx.annotation.Nullable
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.mjnm.themovieapp.data.vos.ProductionCompaniesVO

class ProductionCompanyTypeConverter {
    @TypeConverter
    fun toString(productionCompaniesList : List<ProductionCompaniesVO>?) : String{
        return Gson().toJson(productionCompaniesList)
    }

    @TypeConverter
    fun toProductionCompanies(productionCompaniesListJSONString : String): List<ProductionCompaniesVO>?{
        val productionCompaniesListType = object : TypeToken<List<ProductionCompaniesVO>?>() {}.type
        return Gson().fromJson(productionCompaniesListJSONString,productionCompaniesListType)
    }
}