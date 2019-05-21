package kz.movieapp.moviedb.movie.nowplaying

import kz.movieapp.moviedb.movie.upcoming.UpcomingView

interface NowPlayingPresenter {
    fun setView(nowPlayingView: NowPlayingView)
}