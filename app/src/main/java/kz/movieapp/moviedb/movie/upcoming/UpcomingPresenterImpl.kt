package kz.movieapp.moviedb.movie.upcoming

import android.util.Log
import kz.movieapp.moviedb.api.MovieResponse
import kz.movieapp.moviedb.movie.MovieInteractor
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class UpcomingPresenterImpl(private val interactor: MovieInteractor, private var view: UpcomingView?): UpcomingPresenter {

    override fun setView(nowPlayingView: UpcomingView) {
        view = nowPlayingView
        getNowPlayingMovies()
    }

    private fun getNowPlayingMovies(){
        interactor.getUpcomingMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { popularMoviesResponse -> onGetNowPlayingMoviesSuccess(popularMoviesResponse)},
                { e -> onGetNowPlayingMoviesFailure(e) }
            )
    }

    private fun onGetNowPlayingMoviesFailure(e: Throwable?) {
        Log.e(e?.message, e?.stackTrace.toString())
    }

    private fun onGetNowPlayingMoviesSuccess(moviesResponse: MovieResponse?) {
        view?.showUpcomingMovies(moviesResponse?.movies)
    }
}