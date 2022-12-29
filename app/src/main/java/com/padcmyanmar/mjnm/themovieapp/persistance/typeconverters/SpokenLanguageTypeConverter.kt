package com.padcmyanmar.mjnm.themovieapp.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.mjnm.themovieapp.data.vos.SpokenLanguagesVO

class SpokenLanguageTypeConverter {
    @TypeConverter
    fun toString(spokenLanguageList : List<SpokenLanguagesVO>?) : String{
        return Gson().toJson(spokenLanguageList)
    }

    @TypeConverter
    fun toSpokenLanguageList(spokenLanguageListJSONString : String): List<SpokenLanguagesVO>?{
        val spokenLanguageListType = object : TypeToken<List<SpokenLanguagesVO>?>() {}.type
        return Gson().fromJson(spokenLanguageListJSONString,spokenLanguageListType)
    }
}