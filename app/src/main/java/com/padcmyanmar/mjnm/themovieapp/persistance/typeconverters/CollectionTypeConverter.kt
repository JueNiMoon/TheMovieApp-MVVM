package com.padcmyanmar.mjnm.themovieapp.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.mjnm.themovieapp.data.vos.CollectionVO

class CollectionTypeConverter {
    @TypeConverter
    fun toString(collection: CollectionVO?): String{
        return Gson().toJson(collection)
    }

    @TypeConverter
    fun toCollectionVO(commentListJsonStr : String) : CollectionVO?{
        val collectionVOType = object : TypeToken<CollectionVO?>(){}.type
        return Gson().fromJson(commentListJsonStr,collectionVOType)
    }
}