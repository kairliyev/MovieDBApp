package kz.movieapp.moviedb.detail

import kz.movieapp.moviedb.models.MovieDetail
import kz.movieapp.moviedb.models.Videos

interface DetailView {
    fun showMovieDetails(movies : MovieDetail?)
    fun getVideos(videos: List<Videos>?)
}