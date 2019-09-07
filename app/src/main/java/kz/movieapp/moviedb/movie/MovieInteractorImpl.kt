package kz.movieapp.moviedb.movie

import kz.movieapp.moviedb.api.MovieDbApi
import kz.movieapp.moviedb.models.response.MovieResponse
import kz.movieapp.moviedb.models.Movie
import kz.movieapp.moviedb.models.response.GenreList
import kz.movieapp.moviedb.utils.Language
import rx.Observable

class MovieInteractorImpl(private val movieDbApi: MovieDbApi) : MovieInteractor {

    override fun getGenreList(): Observable<GenreList> {
        return movieDbApi.getGenreList(createGenreListQueryMap())
    }

    override fun getUpcomingMovies(): Observable<MovieResponse> {
        return movieDbApi.getUpcomingMovie(createQueryMap())
    }

    override fun getPopularMovies(): Observable<MovieResponse> {
        return movieDbApi.getPopularMovie(createQueryMap())
    }

    override fun getNowPlayingMovies(): Observable<MovieResponse> {
        return movieDbApi.getNowPlayingMovie(createQueryMap())
    }

    override fun getLatestMovies(): Observable<Movie> {
        return movieDbApi.getLatestMovie(createQueryMap())
    }

    override fun getGenreFilter(): Observable<MovieResponse> {
        return movieDbApi.getGenreFilter(createGenreQueryMap())
    }

    fun createGenreListQueryMap(): Map<String, String> {
        return hashMapOf(
            "language" to "ru"
        )
    }

    fun createGenreQueryMap(): Map<String, String> {
        return hashMapOf(
            "language" to "ru",
            "sort_by" to "popularity.desc"
        )
    }

    private fun createQueryMap(): Map<String, String> {
        return hashMapOf(
            "language" to "ru",
            "sort_by" to "popularity.desc"
        )
    }
}

