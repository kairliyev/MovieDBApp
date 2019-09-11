package kz.movieapp.moviedb.movie.nowplaying

import kz.movieapp.moviedb.models.response.MovieResponse
import kz.movieapp.moviedb.movie.MovieInteractor
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class NowPlayingPresenterImpl(private val interactor: MovieInteractor, private var view: NowPlayingView?): NowPlayingPresenter {

    override fun setView(
        nowPlayingView: NowPlayingView,
        from: String,
        to: String,
        request: String
    ) {
        view = nowPlayingView
        getFilteredMovie(from, to, request)
    }

    fun getFilteredMovie(from: String, to: String, request: String) {
        var fr = ""
        var t = ""

        if (from.length <= 3)
            fr = "1990-01-01"
        else
            fr = "$from-01-01"
        if (to.length <= 3)
            t = "2020-01-01"
        else
            t = "$to-01-01"


        interactor.getMovieFilter(fr, t, request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response -> onGetMovieFilterListSuccess(response) },
                { e -> onGetMovieFilterFailure(e) }
            )
    }

    private fun onGetMovieFilterFailure(e: Throwable?) {

    }

    private fun onGetMovieFilterListSuccess(response: MovieResponse?) {
        view?.showFilteredMovies(response)
    }
}