package com.padcmyanmar.mjnm.themovieapp.data.models

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import com.padcmyanmar.mjnm.themovieapp.data.vos.*
import com.padcmyanmar.mjnm.themovieapp.network.dataagents.MovieDataAgent
//import com.padcmyanmar.mjnm.themovieapp.network.dataagents.RetrofitDataAgentImpl
import com.padcmyanmar.mjnm.themovieapp.persistance.MovieDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

object MovieModelImpl : BaseModel(),MovieModel {

    @SuppressLint("CheckResult")
    override fun getNowPlayingMovies(
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>? {
        //Network
        mMovieApi.getNowPlayingMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                it.results?.forEach { movie -> movie.type = NOW_PLAYING }
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())

//                onSuccess(it.results ?: listOf())
            }, {
                onFailure(it.localizedMessage ?: "")
            })

        return mMovieDatabase?.movieDao()?.getMovieByType(type = NOW_PLAYING)
    }

    @SuppressLint("CheckResult")
    override fun getPopularMovies(onFailure: (String) -> Unit): LiveData<List<MovieVO>>? {
        //Database
//        onSuccess(mMovieDatabase?.movieDao()?.getMovieByType(type = POPULAR)?: listOf())

        //Network
        mMovieApi.getPopularMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                it.results?.forEach{ movie-> movie.type = POPULAR }
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())
//                onSuccess(it.results ?: listOf())
            }, {
                onFailure(it.localizedMessage ?: "")
            })

        return mMovieDatabase?.movieDao()?.getMovieByType(type = POPULAR)
    }

    @SuppressLint("CheckResult")
    override fun getTopRatedMovies(
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>? {

        //Network
        mMovieApi.getTopRatedMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                it.results?.forEach{ movie-> movie.type = TOP_RATED }
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())

            }, {
                onFailure(it.localizedMessage ?: "")
            })

        return mMovieDatabase?.movieDao()?.getMovieByType(type = TOP_RATED)
    }

    @SuppressLint("CheckResult")
    override fun getGenre(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
        //Network
        mMovieApi.getGenres()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                onSuccess(it.genres ?: listOf())
            }, {
                onFailure(it.localizedMessage ?: "")
            })
    }

    @SuppressLint("CheckResult")
    override fun getMovieByGenre(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieApi.getMoviesByGenre(genreId = genreId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                onSuccess(it.results ?: listOf())
            }, {
                onFailure(it.localizedMessage ?: "")
            })
    }

    @SuppressLint("CheckResult")
    override fun getActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieApi.getActors()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                onSuccess(it.results ?: listOf())
            }, {
                onFailure(it.localizedMessage ?: "")
            })
    }

    @SuppressLint("CheckResult")
    override fun getMovieDetails(
        movieId: String,
        onFailure: (String) -> Unit
    ): LiveData<MovieVO?>? {
         mMovieApi.getMovieDetails(movieId = movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                val movieFromDatabase = mMovieDatabase?.movieDao()?.getMovieByIdOneTime(movieId = movieId.toInt())
                it.type = movieFromDatabase?.type
                mMovieDatabase?.movieDao()?.insertStringMovie(it)
            }, {
                onFailure(it.localizedMessage ?: "")
            })

        return mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
    }

    override fun getCreditsByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieApi.getCreditByMovie(movieId = movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                onSuccess(Pair(it.cast ?: listOf(),it.crew ?: listOf()))
            }, {
                onFailure(it.localizedMessage ?: "")
            })

    }

    @SuppressLint("CheckResult")
    override fun searchMovie(
        query: String
    ): Observable<List<MovieVO>> {
        return mMovieApi
            .searchMovie(query = query)
            .map { it.results ?: listOf() }
            .onErrorResumeNext { Observable.just(listOf()) }
            .subscribeOn(Schedulers.io())
    }


}