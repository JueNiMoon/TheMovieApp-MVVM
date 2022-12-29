package com.padcmyanmar.mjnm.themovieapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.mjnm.themovieapp.R
import com.padcmyanmar.mjnm.themovieapp.data.vos.MovieVO
import com.padcmyanmar.mjnm.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.padcmyanmar.mjnm.themovieapp.viewholders.ShowcaseViewHolder

class ShowcaseAdapter(private val mDelegate: ShowcaseViewHolderDelegate) : RecyclerView.Adapter<ShowcaseViewHolder>() {

    private var mMovieList: List<MovieVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowcaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_showcase,parent,false)
        return ShowcaseViewHolder(view, mDelegate)
    }

    override fun onBindViewHolder(holder: ShowcaseViewHolder, position: Int) {
        if (mMovieList.isNotEmpty()){
            holder.bindData(mMovieList[position])
        }
    }

    override fun getItemCount(): Int {
        return  mMovieList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(movieList: List<MovieVO>){
        mMovieList = movieList
        notifyDataSetChanged()
    }
}