package kz.movieapp.moviedb.movie.favorites

import kz.movieapp.moviedb.models.Movie

interface FavoriteFragmentView {
    fun showFavoriteFragmentMovies(movies : ArrayList<Movie>)
}