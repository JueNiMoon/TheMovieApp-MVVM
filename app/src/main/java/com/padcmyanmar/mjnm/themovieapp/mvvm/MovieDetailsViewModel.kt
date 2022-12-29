package com.padcmyanmar.mjnm.themovieapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.padcmyanmar.mjnm.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.mjnm.themovieapp.data.vos.ActorVO
import com.padcmyanmar.mjnm.themovieapp.data.vos.MovieVO

class MovieDetailsViewModel : ViewModel(){
    // Model
    private val mMovieModel = MovieModelImpl

    // Live Data
    var movieDetailsLiveData: LiveData<MovieVO?>? = null
    var castLiveData = MutableLiveData<List<ActorVO>>()
    var crewLiveData = MutableLiveData<List<ActorVO>>()
    var mErrorLiveData = MutableLiveData<String>()

    fun getInitialData(movieId: Int){
        movieDetailsLiveData = mMovieModel.getMovieDetails(movieId = movieId.toString()){
            mErrorLiveData.postValue(it)
        }

        mMovieModel.getCreditsByMovie(movieId = movieId.toString(), onSuccess = {
            castLiveData.postValue(it.first ?: listOf())
            crewLiveData.postValue(it.second ?: listOf())
        }, onFailure = {
            mErrorLiveData.postValue(it)
        })
    }
}