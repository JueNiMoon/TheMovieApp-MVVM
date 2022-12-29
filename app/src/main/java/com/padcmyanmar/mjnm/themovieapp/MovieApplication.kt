package com.padcmyanmar.mjnm.themovieapp

import android.app.Application
import com.padcmyanmar.mjnm.themovieapp.data.models.MovieModelImpl

class MovieApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        MovieModelImpl.initDatabase(applicationContext)
    }
}