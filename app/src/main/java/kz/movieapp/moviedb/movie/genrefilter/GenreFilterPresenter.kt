package kz.movieapp.moviedb.movie.genrefilter

import kz.movieapp.moviedb.movie.latestmovie.LatestMovieView

interface GenreFilterPresenter {
    fun setView(genreFilterView: GenreFilterView)
}