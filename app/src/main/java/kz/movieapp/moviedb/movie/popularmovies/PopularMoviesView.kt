package kz.movieapp.moviedb.movie.popularmovies

import kz.movieapp.moviedb.models.Movie

interface PopularMoviesView {
    fun showPopularMovies(movies : List<Movie>?)
}