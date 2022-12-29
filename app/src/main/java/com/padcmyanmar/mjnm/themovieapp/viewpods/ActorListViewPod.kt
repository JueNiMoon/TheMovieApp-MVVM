package com.padcmyanmar.mjnm.themovieapp.viewpods

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.mjnm.themovieapp.adapters.ActorAdapter
import com.padcmyanmar.mjnm.themovieapp.data.vos.ActorVO
import com.padcmyanmar.mjnm.themovieapp.data.vos.MovieVO
import kotlinx.android.synthetic.main.view_pod_actor_list.view.*

class ActorListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    lateinit var mActorAdapter : ActorAdapter

    override fun onFinishInflate() {
        setupActorRecyclerView()
        super.onFinishInflate()
    }

    fun setUpActorViewPod(backgroundcolorReference: Int, titleText: String,moreTitleText: String){
        setBackgroundColor(ContextCompat.getColor(context,backgroundcolorReference))
        tvBestActors.text = titleText
        tvMoreActors.text = moreTitleText
        tvMoreActors.paintFlags = Paint.UNDERLINE_TEXT_FLAG

    }

    private fun setupActorRecyclerView()
    {
        mActorAdapter = ActorAdapter()
        rvBestactors.adapter = mActorAdapter
        rvBestactors.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
    }


    fun setData(actors: List<ActorVO>){
        mActorAdapter.setNewData(actors)
    }
}