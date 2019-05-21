package kz.movieapp.moviedb.movie

import kz.movieapp.moviedb.api.MovieResponse
import kz.movieapp.moviedb.api.SingleMovieResponse
import kz.movieapp.moviedb.models.Movie
import rx.Observable

interface MovieInteractor {
    fun getNowPlayingMovies(): Observable<MovieResponse>
    fun getUpcomingMovies(): Observable<MovieResponse>
    fun getPopularMovies(): Observable<MovieResponse>
    fun getLatestMovies(): Observable<Movie>
    fun getGenreFilter(): Observable<MovieResponse>
}