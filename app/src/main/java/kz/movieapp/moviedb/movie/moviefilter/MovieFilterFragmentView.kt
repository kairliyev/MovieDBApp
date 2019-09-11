package kz.movieapp.moviedb.movie.moviefilter

import kz.movieapp.moviedb.models.response.GenreList
import kz.movieapp.moviedb.models.response.MovieResponse

interface MovieFilterFragmentView {
    fun showGenreList(genreList: GenreList)
    fun loading()
    fun hideLoading()
    fun showResult(movieResponse: MovieResponse?)
}