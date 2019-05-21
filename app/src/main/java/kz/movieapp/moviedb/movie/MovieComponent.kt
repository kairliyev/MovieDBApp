package kz.movieapp.moviedb.movie

import dagger.Subcomponent
import kz.movieapp.moviedb.di.AppScope
import kz.movieapp.moviedb.movie.favorites.FavoriteFragment
import kz.movieapp.moviedb.movie.genrefilter.GenreFilter
import kz.movieapp.moviedb.movie.latestmovie.LatestMovie
import kz.movieapp.moviedb.movie.nowplaying.NowPlaying
import kz.movieapp.moviedb.movie.popularmovies.PopularMovies
import kz.movieapp.moviedb.movie.upcoming.UpcomingFragment

@AppScope
@Subcomponent(modules = [(MovieModule::class)])
interface MovieComponent {
//    fun inject(target: NowPlayingFragment)
    fun inject(target: UpcomingFragment)
    fun inject(target: NowPlaying)
    fun inject(target: PopularMovies)
    fun inject(target: LatestMovie)
    fun inject(target: GenreFilter)
    fun inject(target: FavoriteFragment)
}