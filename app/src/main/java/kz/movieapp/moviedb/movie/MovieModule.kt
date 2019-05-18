package kz.movieapp.moviedb.movie

import dagger.Module
import dagger.Provides
import kz.movieapp.moviedb.api.MovieDbApi
import kz.movieapp.moviedb.di.AppScope
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
}