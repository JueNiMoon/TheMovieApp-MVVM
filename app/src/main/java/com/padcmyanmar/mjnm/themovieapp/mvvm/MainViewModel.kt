package com.padcmyanmar.mjnm.themovieapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.padcmyanmar.mjnm.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.mjnm.themovieapp.data.vos.ActorVO
import com.padcmyanmar.mjnm.themovieapp.data.vos.GenreVO
import com.padcmyanmar.mjnm.themovieapp.data.vos.MovieVO

class MainViewModel : ViewModel(){

    //Model
    private val mMovieModel = MovieModelImpl

    //Live Data
    var nowPlayingMovieLiveData: LiveData<List<MovieVO>>? = null
    var popularMoviesLiveData: LiveData<List<MovieVO>>? = null
    var topRatedMoviesLiveData: LiveData<List<MovieVO>>? = null
    val genreLiveData = MutableLiveData<List<GenreVO>>()
    val moviesByGenreLiveData = MutableLiveData<List<MovieVO>>()
    val actorsLiveData = MutableLiveData<List<ActorVO>>()
    val mErrorLiveData = MutableLiveData<String>()

    fun getInitialData(){
        nowPlayingMovieLiveData = mMovieModel.getNowPlayingMovies { mErrorLiveData.postValue(it) }
        popularMoviesLiveData = mMovieModel.getPopularMovies { mErrorLiveData.postValue(it) }
        topRatedMoviesLiveData = mMovieModel.getTopRatedMovies { mErrorLiveData.postValue(it) }

        mMovieModel.getGenre(
            onSuccess = {
                genreLiveData.postValue(it)
                getMovieByGenre(0)
            },
            onFailure = {
                mErrorLiveData.postValue(it)
            }
        )
    }

    fun getMovieByGenre(genrePosition: Int){
        genreLiveData.value?.getOrNull(genrePosition)?.id?.let {
            mMovieModel.getMovieByGenre(
                genreId = it.toString(),
                onSuccess = { movieByGenre ->
                moviesByGenreLiveData.postValue(movieByGenre)
            }, onFailure = {
                    mErrorLiveData.postValue(it)
                })
        }
    }
}