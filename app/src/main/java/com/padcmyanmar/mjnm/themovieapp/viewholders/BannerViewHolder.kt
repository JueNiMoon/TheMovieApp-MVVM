package com.padcmyanmar.mjnm.themovieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.mjnm.themovieapp.data.vos.MovieVO
import com.padcmyanmar.mjnm.themovieapp.delegates.BannerViewHolderDelegate
import com.padcmyanmar.mjnm.themovieapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_item_banner.view.*

class BannerViewHolder(itemView: View, private val mDelegate: BannerViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mMovieVO: MovieVO? = null
    init {
        itemView.setOnClickListener {
            mMovieVO?.let {
                mDelegate.onTapMovieFromBanner(it.id)
            }
        }
    }

    fun bindData(movie: MovieVO){

        mMovieVO = movie
        Glide.with(itemView.context)
            .load("${IMAGE_BASE_URL}${movie.posterPath}")
            .into(itemView.ivBanner)

        itemView.tvBannerMovieName.text = movie.title
    }
}