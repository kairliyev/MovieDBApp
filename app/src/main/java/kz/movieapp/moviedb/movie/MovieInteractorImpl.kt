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
        return movieDbApi.getUpcomingMovie(createQueryMap(1))
    }

    override fun getPopularMovies(page: Int): Observable<MovieResponse> {
        return movieDbApi.getPopularMovie(createQueryMap(page))
    }

    override fun getNowPlayingMovies(): Observable<MovieResponse> {
        return movieDbApi.getNowPlayingMovie(createQueryMap(1))
    }

    override fun getLatestMovies(): Observable<Movie> {
        return movieDbApi.getLatestMovie(createQueryMap(1))
    }

    override fun getGenreFilter(id: String): Observable<MovieResponse> {
        return movieDbApi.getGenreFilter(createGenreFilterQueryMap(id))
    }

    fun createGenreListQueryMap(): Map<String, String> {
        return hashMapOf(
            "language" to "ru"
        )
    }

    fun createGenreFilterQueryMap(id: String): Map<String, String> {
        return hashMapOf(
            "language" to "ru",
            "with_genres" to id,
            "sort_by" to "popularity.desc"
        )
    }

    private fun createQueryMap(page: Int): Map<String, String> {
        return hashMapOf(
            "page" to page.toString(),
            "language" to "ru",
            "sort_by" to "popularity.desc"
        )
    }
}

