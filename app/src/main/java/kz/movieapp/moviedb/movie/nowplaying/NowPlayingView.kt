package kz.movieapp.moviedb.movie.nowplaying


import kz.movieapp.moviedb.models.Movie

interface NowPlayingView {
    fun showNowPlayingMovies(movies : ArrayList<Movie>?)
}