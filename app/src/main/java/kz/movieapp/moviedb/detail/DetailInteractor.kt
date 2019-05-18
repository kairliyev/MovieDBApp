package kz.movieapp.moviedb.detail

import kz.movieapp.moviedb.api.VideoResponse
import kz.movieapp.moviedb.models.MovieDetail
import rx.Observable

interface DetailInteractor {
    fun getMovieDetails(id: String): Observable<MovieDetail>
    fun getMovieVideos(id: String): Observable<VideoResponse>
}