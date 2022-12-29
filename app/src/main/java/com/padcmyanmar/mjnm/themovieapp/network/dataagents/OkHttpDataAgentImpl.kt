package com.padcmyanmar.mjnm.themovieapp.network.dataagents

import android.os.AsyncTask
import com.google.gson.Gson
import com.padcmyanmar.mjnm.themovieapp.data.vos.ActorVO
import com.padcmyanmar.mjnm.themovieapp.data.vos.GenreVO
import com.padcmyanmar.mjnm.themovieapp.data.vos.MovieVO
import com.padcmyanmar.mjnm.themovieapp.network.responses.MovieListResponse
import com.padcmyanmar.mjnm.themovieapp.utils.API_GET_NOW_PLAYING
import com.padcmyanmar.mjnm.themovieapp.utils.BASE_URL
import com.padcmyanmar.mjnm.themovieapp.utils.MOVIE_API_KEY
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

object OkHttpDataAgentImpl: MovieDataAgent {

    private val mClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(15,TimeUnit.SECONDS)
        .readTimeout(15,TimeUnit.SECONDS)
        .writeTimeout(15,TimeUnit.SECONDS)
        .build()

//    override fun getNowPlayingMovies() {
//        GetNowPlayingMovieOkHttpTask(mClient).execute()
//    }

    class GetNowPlayingMovieOkHttpTask(
        private val mOkHttpClient: OkHttpClient
    ) :
        AsyncTask<Void,Void,MovieListResponse>(){


        override fun onPostExecute(result: MovieListResponse?) {
            super.onPostExecute(result)
        }

        override fun doInBackground(vararg params: Void?): MovieListResponse? {
            val request = Request.Builder()
                .url("""$BASE_URL$API_GET_NOW_PLAYING?api_key=$MOVIE_API_KEY&language=en-US&page=1""")
                .build()

            try {
                val response = mOkHttpClient.newCall(request).execute()

                if (response.isSuccessful){
                    response.body?.let {
                        val responseString = it.string()
                        val response = Gson().fromJson<MovieListResponse>(
                            responseString,
                            MovieListResponse::class.java
                        )
                        return response
                    }
                }
            }catch (e: Exception){
                e.printStackTrace()
            }

            return null
        }

    }

    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

    }

    override fun getPopularMovies(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {

    }

    override fun getTopRatedMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

    }

    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {

    }

    override fun getMoviesByGenre(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

    }

    override fun getActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {

    }

    override fun getMovieDetails(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {

    }

    override fun getCreditsByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {

    }

}