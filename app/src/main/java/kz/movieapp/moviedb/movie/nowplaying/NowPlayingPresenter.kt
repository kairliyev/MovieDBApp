package kz.movieapp.moviedb.movie.nowplaying

interface NowPlayingPresenter {
    fun setView(
        nowPlayingView: NowPlayingView,
        from: String,
        to: String,
        request: String
    )
}