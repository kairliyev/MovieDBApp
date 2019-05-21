package kz.movieapp.moviedb.movie

import kz.movieapp.moviedb.api.MovieDbApi
import kz.movieapp.moviedb.api.MovieResponse
import kz.movieapp.moviedb.models.Movie
import kz.movieapp.moviedb.utils.Language
import rx.Observable

class MovieInteractorImpl(private val movieDbApi: MovieDbApi) : MovieInteractor {

    var arr: Map<String, String> = hashMapOf()

    fun addGenreQueryMap(aa: Map<String, String>) {
        arr = aa
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

    fun createGenreQueryMap(): Map<String, String> {
        return hashMapOf(
            "language" to Language.language,
            "sort_by" to "popularity.desc"
        )
    }

    private fun createQueryMap(): Map<String, String> {
        return hashMapOf(
            "language" to Language.language,
            "sort_by" to "popularity.desc"
        )
    }
}

object MovieGenre {
    var movieMap: HashMap<String, String> = hashMapOf()
}

object MovieCustomQuery {
    var movieQueryLanguageRU: HashMap<String, String> = hashMapOf(
        "language" to Language.language,
        "sort_by" to "popularity.desc"
    )
}