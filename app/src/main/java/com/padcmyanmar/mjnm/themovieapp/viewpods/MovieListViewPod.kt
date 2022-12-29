package com.padcmyanmar.mjnm.themovieapp.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.mjnm.themovieapp.adapters.MovieAdapter
import com.padcmyanmar.mjnm.themovieapp.data.vos.MovieVO
import com.padcmyanmar.mjnm.themovieapp.delegates.MovieViewHolderDelegate
import kotlinx.android.synthetic.main.view_pod_movie_list.view.*

class MovieListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    lateinit var mMovieAdapter : MovieAdapter
    lateinit var mDelegate : MovieViewHolderDelegate

    override fun onFinishInflate() {
       // setUpMovieRecyclerView() // call after getting delegate activity
        super.onFinishInflate()
    }

    // must public bcoz this fun will be called from activity
    fun setUpMovieListViewPod(delegate: MovieViewHolderDelegate){
        setDelegate(delegate)
        setUpMovieRecyclerView()
    }

    private fun setDelegate(delegate: MovieViewHolderDelegate){
        this.mDelegate = delegate
    }

    private fun setUpMovieRecyclerView(){
        mMovieAdapter = MovieAdapter(mDelegate)
        rvMovieList.adapter = mMovieAdapter
        rvMovieList.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

    }

    fun setData(movieList: List<MovieVO>){
        mMovieAdapter.setNewData(movieList)
    }
}