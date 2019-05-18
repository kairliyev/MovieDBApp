package kz.movieapp.moviedb.api

import kz.movieapp.moviedb.models.MovieDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import rx.Observable

interface MovieDbApi {
    @GET("movie/now_playing")
    fun getNowPlayingMovie(@QueryMap map : Map<String, String>): Observable<MovieResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovie(@QueryMap map : Map<String, String>): Observable<MovieResponse>

    @GET("movie/popular")
    fun getPopularMovie(@QueryMap map : Map<String, String>): Observable<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: String, @QueryMap map: Map<String, String>): Observable<MovieDetail>

    @GET("movie/{movie_id}/videos")
    fun getMovieVideos(@Path("movie_id") id: String): Observable<VideoResponse>
}