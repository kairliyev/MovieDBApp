package kz.movieapp.moviedb.movie

import kz.movieapp.moviedb.api.MovieResponse
import rx.Observable

interface MovieInteractor {
    fun getNowPlayingMovies(): Observable<MovieResponse>
    fun getUpcomingMovies(): Observable<MovieResponse>
    fun getPopularMovies(): Observable<MovieResponse>
}