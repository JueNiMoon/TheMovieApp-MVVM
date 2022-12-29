package com.padcmyanmar.mjnm.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.mjnm.themovieapp.data.vos.ActorVO

data class GetCreditsByMovieResponse(
    @SerializedName("cast")
    val cast: List<ActorVO>?,
    @SerializedName("crew")
    val crew: List<ActorVO>?,
)