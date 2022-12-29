package com.padcmyanmar.mjnm.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.mjnm.themovieapp.data.vos.ActorVO

data class GetActorResponse (
    @SerializedName("results")
    val results: List<ActorVO>?
)