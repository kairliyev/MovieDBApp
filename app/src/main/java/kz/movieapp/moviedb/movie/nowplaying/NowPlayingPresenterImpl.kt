package kz.movieapp.moviedb.movie.nowplaying

import android.util.Log
import kz.movieapp.moviedb.models.response.MovieResponse
import kz.movieapp.moviedb.movie.MovieInteractor
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class NowPlayingPresenterImpl(private val interactor: MovieInteractor, private var view: NowPlayingView?): NowPlayingPresenter {

    override fun setView(nowPlayingView: NowPlayingView) {
        view = nowPlayingView
        getNowPlayingMovies()
    }

    private fun getNowPlayingMovies(){
        interactor.getNowPlayingMovies()
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
        view?.showNowPlayingMovies(moviesResponse?.movies)
    }
}