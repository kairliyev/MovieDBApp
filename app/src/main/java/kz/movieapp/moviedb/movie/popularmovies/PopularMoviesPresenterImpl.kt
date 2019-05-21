package kz.movieapp.moviedb.movie.popularmovies

import android.util.Log
import kz.movieapp.moviedb.api.MovieResponse
import kz.movieapp.moviedb.movie.MovieInteractor
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class PopularMoviesPresenterImpl(private val interactor: MovieInteractor, private var view: PopularMoviesView?): PopularMoviesPresenter {

    override fun setView(PopularMoviesView: PopularMoviesView) {
        view = PopularMoviesView
        getPopularMoviesMovies()
    }

    private fun getPopularMoviesMovies(){
        interactor.getPopularMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { popularMoviesResponse -> onGetPopularMoviesMoviesSuccess(popularMoviesResponse)},
                { e -> onGetPopularMoviesMoviesFailure(e) }
            )
    }

    private fun onGetPopularMoviesMoviesFailure(e: Throwable?) {
        Log.e(e?.message, e?.stackTrace.toString())
    }

    private fun onGetPopularMoviesMoviesSuccess(moviesResponse: MovieResponse?) {
        view?.showPopularMovies(moviesResponse?.movies)
    }
}