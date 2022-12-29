package com.padcmyanmar.mjnm.themovieapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.padcmyanmar.mjnm.themovieapp.R
import com.padcmyanmar.mjnm.themovieapp.data.models.MovieModel
import com.padcmyanmar.mjnm.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.mjnm.themovieapp.data.vos.GenreVO
import com.padcmyanmar.mjnm.themovieapp.data.vos.MovieVO
import com.padcmyanmar.mjnm.themovieapp.mvvm.MovieDetailsViewModel
import com.padcmyanmar.mjnm.themovieapp.utils.IMAGE_BASE_URL
import com.padcmyanmar.mjnm.themovieapp.viewpods.ActorListViewPod
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.activity_movie_details.tvMovieName
import kotlinx.android.synthetic.main.view_holder_movie.*

class MovieDetailsActivity : AppCompatActivity() {

    companion object{

        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"

        fun newIntent(context: Context, movieId: Int): Intent {
            //return Intent(context, MovieDetailsActivity::class.java)

            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            return intent
        }
    }

    //view pod
    lateinit var actorsViewPod : ActorListViewPod
    lateinit var creatorViewPod: ActorListViewPod

    //View Model
    private lateinit var mViewModel: MovieDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movieId = intent?.getIntExtra(EXTRA_MOVIE_ID,0)
        movieId?.let {
            setUpViewModel(it)
        }

        setUpViewPods()
        setUpListener()
        observeLiveData()
    }

    private fun setUpViewModel(movieId : Int){
        mViewModel = ViewModelProvider(this)[MovieDetailsViewModel::class.java]
        mViewModel.getInitialData(movieId = movieId)
    }

    private fun observeLiveData(){
        mViewModel.movieDetailsLiveData?.observe(this){
            it?.let { movie-> bindData(movie) }
        }
        mViewModel.castLiveData.observe(this,actorsViewPod::setData)
        mViewModel.crewLiveData.observe(this,creatorViewPod::setData)
    }


    private fun bindData(movie : MovieVO){
        Glide.with(this)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(ivMovieDetails)
        tvMovieName.text = movie.title ?: ""
        tvMovieReleaseYear.text = movie.releaseDate?.substring(0,4)
        tvRating.text = movie.voteAverage?.toString()?:""
        movie.voteCount?.let {
            tvNumbersofVotes.text = "$it VOTES"
        }
        rbRatingMovieDetials.rating = movie.getRatingBasedOnFiveStars()

        bindGenres(movie, movie.genres ?: listOf())

        tvOverview.text = movie.overView?:""
        tvOriginalTitle.text = movie.title ?: ""
        tvType.text = movie.getGenresAsCommaSeparatedString()
        tvProduction.text = movie.getProductionCountriesAsCommaSeparatedString()
        tvPremiere.text = movie.releaseDate ?: ""
        tvDescription.text = movie.overView ?: ""
    }

    private fun bindGenres(movie: MovieVO,genres: List<GenreVO>){
        movie.genres?.count()?.let{
            tvFirstGenre.text = genres.firstOrNull()?.name ?: ""
            tvSecondGenre.text = genres.getOrNull(1)?.name ?: ""
            tvThirdGenre.text = genres.getOrNull(2)?.name ?: ""

            if(it< 3){
                tvThirdGenre.visibility = View.GONE
            }else if (it < 2) {
                tvSecondGenre.visibility = View.GONE
            }
        }
    }

    private fun setUpListener(){
        ivSearch.setOnClickListener {
            startActivity(MovieSearchActivity.newIntent(this))
        }
        btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun showError(message: String){
        Snackbar.make(window.decorView,message,Snackbar.LENGTH_LONG).show()
    }

    private fun setUpViewPods(){
        actorsViewPod = vpActors as ActorListViewPod
        actorsViewPod.setUpActorViewPod(
            backgroundcolorReference =  R.color.colorPrimary,
            titleText = getString(R.string.lbl_actors),
            moreTitleText = ""
        )

        creatorViewPod = vpCreators as ActorListViewPod
        creatorViewPod.setUpActorViewPod(
            backgroundcolorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_creators),
            moreTitleText = getString(R.string.lbl_more_creators)
        )
    }
}