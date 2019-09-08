package kz.movieapp.moviedb.movie.genrefilter

import android.util.Log
import kz.movieapp.moviedb.models.response.MovieResponse
import kz.movieapp.moviedb.movie.MovieInteractor
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class GenreFilterPresenterImpl(private val interactor: MovieInteractor, private var view: GenreFilterView?): GenreFilterPresenter {

    override fun setView(genreFilterView: GenreFilterView, id: String) {
        view = genreFilterView
        getGenreFilterMovies(id)
    }

    private fun getGenreFilterMovies(id: String){
        interactor.getGenreFilter(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { popularMoviesResponse -> onGetGenreFilterMoviesSuccess(popularMoviesResponse)},
                { e -> onGetGenreFilterMoviesFailure(e) }
            )
    }

    private fun onGetGenreFilterMoviesFailure(e: Throwable?) {
        Log.e(e?.message, e?.stackTrace.toString())
    }

    private fun onGetGenreFilterMoviesSuccess(movieResponse: MovieResponse) {
        view?.showGenreFilterMovie(movieResponse.movies)
    }
}