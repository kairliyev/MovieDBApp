package kz.movieapp.moviedb.movie

import dagger.Module
import dagger.Provides
import kz.movieapp.moviedb.api.MovieDbApi
import kz.movieapp.moviedb.di.AppScope
import kz.movieapp.moviedb.movie.genrefilter.GenreFilterPresenter
import kz.movieapp.moviedb.movie.genrefilter.GenreFilterPresenterImpl
import kz.movieapp.moviedb.movie.latestmovie.LatestMoviePresenter
import kz.movieapp.moviedb.movie.latestmovie.LatestMoviePresenterImpl
import kz.movieapp.moviedb.movie.nowplaying.NowPlaying
import kz.movieapp.moviedb.movie.nowplaying.NowPlayingPresenter
import kz.movieapp.moviedb.movie.nowplaying.NowPlayingPresenterImpl
import kz.movieapp.moviedb.movie.popularmovies.PopularMoviesPresenter
import kz.movieapp.moviedb.movie.popularmovies.PopularMoviesPresenterImpl
import kz.movieapp.moviedb.movie.upcoming.UpcomingPresenter
import kz.movieapp.moviedb.movie.upcoming.UpcomingPresenterImpl

@Module
class MovieModule {
//    @Provides
//    fun provideMainPresenter(movieInteractor: MovieInteractor): NowPlayingPresenter {
//        return NowPlayingPresenterImpl(movieInteractor, null)
//    }

    @Provides
    @AppScope
    fun provideMainInteractor(api : MovieDbApi): MovieInteractor {
        return MovieInteractorImpl(api)
    }

    @Provides
    fun provideUpcomingPresenter(movieInteractor: MovieInteractor): UpcomingPresenter {
        return UpcomingPresenterImpl(movieInteractor, null)
    }

    @Provides
    fun provideNowPlayingPresenter(movieInteractor: MovieInteractor): NowPlayingPresenter {
        return NowPlayingPresenterImpl(movieInteractor, null)
    }
    @Provides
    fun providePopularMoviePresenter(movieInteractor: MovieInteractor): PopularMoviesPresenter {
        return PopularMoviesPresenterImpl(movieInteractor, null)
    }
    @Provides
    fun provideLatestMoviePresenter(movieInteractor: MovieInteractor): LatestMoviePresenter {
        return LatestMoviePresenterImpl(movieInteractor, null)
    }

    @Provides
    fun provideGenreFilterPresenter(movieInteractor: MovieInteractor): GenreFilterPresenter {
        return GenreFilterPresenterImpl(movieInteractor, null)
    }

}