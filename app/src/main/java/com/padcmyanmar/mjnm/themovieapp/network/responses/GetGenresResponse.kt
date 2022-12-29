package com.padcmyanmar.mjnm.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.mjnm.themovieapp.data.vos.GenreVO

data class GetGenresResponse (
    @SerializedName("genres")
    val genres: List<GenreVO>?
)