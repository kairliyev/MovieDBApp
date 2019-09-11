package kz.movieapp.moviedb.movie.moviefilter

interface MovieFilterPresenter {
    fun prepareGenreList(view: MovieFilterFragment)
    fun filterMovie(from: String, to: String, request: String)
}