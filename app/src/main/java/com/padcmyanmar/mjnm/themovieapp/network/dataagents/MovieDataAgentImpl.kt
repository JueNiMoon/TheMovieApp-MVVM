package com.padcmyanmar.mjnm.themovieapp.network.dataagents

import android.os.AsyncTask
import android.util.Log
import com.google.gson.Gson
import com.padcmyanmar.mjnm.themovieapp.data.vos.ActorVO
import com.padcmyanmar.mjnm.themovieapp.data.vos.GenreVO
import com.padcmyanmar.mjnm.themovieapp.data.vos.MovieVO
import com.padcmyanmar.mjnm.themovieapp.network.responses.MovieListResponse
import com.padcmyanmar.mjnm.themovieapp.utils.API_GET_NOW_PLAYING
import com.padcmyanmar.mjnm.themovieapp.utils.BASE_URL
import com.padcmyanmar.mjnm.themovieapp.utils.MOVIE_API_KEY
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object MovieDataAgentImpl : MovieDataAgent {
//    override fun getNowPlayingMovies() {
//
//        GetNowPlayingMovieTask().execute()
//    }

    class GetNowPlayingMovieTask: AsyncTask<Void,Void, MovieListResponse?>(){
        override fun doInBackground(vararg params: Void?): MovieListResponse? {

            val url: URL
            var reader: BufferedReader? = null
            var stringBuilder: StringBuilder

            try {
                url = URL ("""$BASE_URL$API_GET_NOW_PLAYING?api_key=$MOVIE_API_KEY&language=en-US&page=1""") //""" -> raw string

                val connection = url.openConnection() as HttpURLConnection

                connection.requestMethod = "GET" //set HTTP method

                connection.readTimeout = 15*1000 // give 15s time out

                connection.doInput  = true // receive return result from network

                // when parameter are given with URL(query parameter,path paremeter) -> false
                // when paremeter are given with request body -> true
                connection.doOutput = false

                connection.connect()

                //Read Data from network
                reader = BufferedReader(
                    InputStreamReader(connection.inputStream)
                )

                //declre empty stringbuilder to assign data from bufferReader
                stringBuilder = StringBuilder()

                for(readline in reader.readLines()){
                    stringBuilder.append(readline + "\n")
                }

                val responseString = stringBuilder.toString()
                Log.d("Now Playing Movie",responseString)

                //fromJson => json to object
                //toJson   => object to json
                val movieListResponse = Gson().fromJson(
                    responseString,
                    MovieListResponse::class.java
                )

                return movieListResponse

            }catch (e: Exception){
                e.printStackTrace()
                Log.e("NewError",e.message?: "")
            }finally {
                if(reader!=null){
                    try {
                        reader.close()

                    }catch (ioe: IOException){
                        ioe.printStackTrace()
                    }
                }
            }
            return null
        }

        override fun onPostExecute(result: MovieListResponse?) {
            super.onPostExecute(result)
        }

    }

    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        GetNowPlayingMovieTask().execute()
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