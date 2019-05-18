package kz.movieapp.moviedb.movie.upcoming

import kz.movieapp.moviedb.models.Movie

interface UpcomingView {
    fun showUpcomingMovies(movies : List<Movie>?)
}