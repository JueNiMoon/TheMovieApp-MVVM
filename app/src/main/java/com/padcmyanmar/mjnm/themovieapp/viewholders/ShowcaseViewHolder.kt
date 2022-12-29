package com.padcmyanmar.mjnm.themovieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.mjnm.themovieapp.data.vos.MovieVO
import com.padcmyanmar.mjnm.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.padcmyanmar.mjnm.themovieapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_showcase.view.*

class ShowcaseViewHolder(itemView: View, private val mDelegate: ShowcaseViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mMovieVO: MovieVO? = null

    init {
        itemView.setOnClickListener {
            mMovieVO?.let {
                mDelegate.onTapMovieFromShowcase(it.id)
            }
        }
    }

    fun bindData(movie: MovieVO){
        mMovieVO = movie

        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(itemView.ivShowCase)
        itemView.tvShowCaseMovieName.text = movie.title
        itemView.tvShowCaseMovieDate.text = movie.releaseDate
    }

}