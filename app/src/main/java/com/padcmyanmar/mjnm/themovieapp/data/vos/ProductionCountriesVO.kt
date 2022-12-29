package com.padcmyanmar.mjnm.themovieapp.data.vos

import com.google.gson.annotations.SerializedName

data class ProductionCountriesVO (
    @SerializedName("iso_3166_1")
    val iso31661: String?,
    @SerializedName("name")
    val name: String?,
)