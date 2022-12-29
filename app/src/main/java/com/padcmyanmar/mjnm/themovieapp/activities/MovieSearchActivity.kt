package com.padcmyanmar.mjnm.themovieapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding4.widget.textChanges
import com.padcmyanmar.mjnm.themovieapp.R
import com.padcmyanmar.mjnm.themovieapp.adapters.MovieAdapter
import com.padcmyanmar.mjnm.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.mjnm.themovieapp.delegates.MovieViewHolderDelegate
import kotlinx.android.synthetic.main.activity_movie_search.*
import java.util.concurrent.TimeUnit

class MovieSearchActivity : AppCompatActivity(), MovieViewHolderDelegate{

    companion object{
        fun newIntent(context : Context) : Intent{
            return Intent(context, MovieSearchActivity::class.java)
        }
    }
    private lateinit var mMovieAdapter: MovieAdapter
    private val mMovieModel = MovieModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_search)

        setUpRecyclerView()
        setUpListeners()
    }

    private fun setUpListeners(){
        etSearch.textChanges()
            .debounce(500L,TimeUnit.MILLISECONDS)
            .flatMap { mMovieModel.searchMovie(it.toString()) }
            .subscribeOn(io.reactivex.rxjava3.schedulers.Schedulers.io())
            .observeOn(io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread())
            .subscribe({
                mMovieAdapter.setNewData(it)
            },{
                showError(it.localizedMessage ?: "")
            })
    }

    private fun setUpRecyclerView(){
        mMovieAdapter = MovieAdapter(this)
        rvMovie.adapter = mMovieAdapter
        rvMovie.layoutManager = GridLayoutManager(this,2)
    }

    private fun showError(message: String){
        Snackbar.make(window.decorView,message, Snackbar.LENGTH_LONG).show()
    }

    override fun onTapMovie(movieId: Int) {

    }

}