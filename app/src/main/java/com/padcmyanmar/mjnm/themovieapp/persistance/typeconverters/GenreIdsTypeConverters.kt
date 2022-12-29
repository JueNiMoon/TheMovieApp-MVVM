package com.padcmyanmar.mjnm.themovieapp.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.mjnm.themovieapp.data.vos.ProductionCompaniesVO

class GenreIdsTypeConverters {
    @TypeConverter
    fun toString(genreIdsList: List<Int>?):String{
        return Gson().toJson(genreIdsList)
    }

    @TypeConverter
    fun toGenreIds(genreIdsJSONString : String) : List<Int>?{
        val genreIdsListType = object : TypeToken<List<Int>?>() {}.type
        return Gson().fromJson(genreIdsJSONString,genreIdsListType)
    }
}