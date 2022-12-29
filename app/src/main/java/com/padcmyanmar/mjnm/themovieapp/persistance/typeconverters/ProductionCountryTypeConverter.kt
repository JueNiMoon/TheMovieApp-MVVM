package com.padcmyanmar.mjnm.themovieapp.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.mjnm.themovieapp.data.vos.ProductionCountriesVO

class ProductionCountryTypeConverter {
    @TypeConverter
    fun toString(productionCountryList : List<ProductionCountriesVO>?) : String{
        return Gson().toJson(productionCountryList)
    }

    @TypeConverter
    fun toProductionCountries(productionCountryListJSONString : String): List<ProductionCountriesVO>?{
        val productionCountryListType = object : TypeToken<List<ProductionCountriesVO>?>() {}.type
        return Gson().fromJson(productionCountryListJSONString,productionCountryListType)
    }
}