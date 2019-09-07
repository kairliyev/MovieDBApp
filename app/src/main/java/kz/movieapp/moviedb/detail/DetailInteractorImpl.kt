package kz.movieapp.moviedb.detail

import kz.movieapp.moviedb.api.MovieDbApi
import kz.movieapp.moviedb.models.response.VideoResponse
import kz.movieapp.moviedb.models.MovieDetail
import kz.movieapp.moviedb.utils.Language
import rx.Observable

class DetailInteractorImpl(private val movieDbApi: MovieDbApi): DetailInteractor{
    override fun getMovieVideos(id: String): Observable<VideoResponse> {
        return movieDbApi.getMovieVideos(id)
    }

    override fun getMovieDetails(id: String): Observable<MovieDetail> {
        return movieDbApi.getMovieDetails(id, createQueryMap())
    }


    private fun createQueryMap(): Map<String, String>{
        return hashMapOf(
            "language" to Language.language
        )
    }
}