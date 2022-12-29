package com.padcmyanmar.mjnm.themovieapp.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.mjnm.themovieapp.data.vos.GenreVO

class GenreListTypeConverter {

    @TypeConverter
    fun toString(genreList : List<GenreVO>?) : String{
        return Gson().toJson(genreList)
    }

    @TypeConverter
    fun toGenreList(genreListJSONString : String): List<GenreVO>?{
        val genreListType = object : TypeToken<List<GenreVO>?>() {}.type
        return Gson().fromJson(genreListJSONString,genreListType)
    }
}