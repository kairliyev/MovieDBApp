package kz.movieapp.moviedb.detail

import android.util.Log
import kz.movieapp.moviedb.api.VideoResponse
import kz.movieapp.moviedb.models.MovieDetail
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class DetailPresenterImpl(val interactor: DetailInteractor, private var view: DetailView?): DetailPresenter{

    override fun setView(mainView: DetailView, id: String) {
        view = mainView
        getMovieDetails(id)
        getMovieVideos(id)
    }

    private fun getMovieDetails(id: String){
        interactor.getMovieDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { movieDetailResponse -> onGetMovieDetailsSuccess(movieDetailResponse)},
                { e -> onGetMovieDetailsFailure(e) }
            )
    }

    private fun onGetMovieDetailsFailure(e: Throwable?) {
        Log.e(e?.message, e?.stackTrace.toString())
    }

    private fun onGetMovieDetailsSuccess(moviesResponse: MovieDetail?) {
        view?.showMovieDetails(moviesResponse)
    }

    private fun getMovieVideos(id: String){
        interactor.getMovieVideos(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { videos -> onGetVideosSuccess(videos)},
                { e -> onGetVideosFailure(e) }
            )
    }


    private fun onGetVideosFailure(e: Throwable?) {
        Log.e(e?.message, e?.stackTrace.toString())
    }

    private fun onGetVideosSuccess(videoResponse: VideoResponse?) {
        view?.getVideos(videoResponse?.videos)
    }

}