package kz.movieapp.moviedb.movie

import kz.movieapp.moviedb.models.response.MovieResponse
import kz.movieapp.moviedb.models.Movie
import kz.movieapp.moviedb.models.response.GenreList
import rx.Observable

interface MovieInteractor {
    fun getNowPlayingMovies(): Observable<MovieResponse>
    fun getUpcomingMovies(): Observable<MovieResponse>
    fun getPopularMovies(): Observable<MovieResponse>
    fun getLatestMovies(): Observable<Movie>
    fun getGenreFilter(): Observable<MovieResponse>
    fun getGenreList():Observable<GenreList>
}