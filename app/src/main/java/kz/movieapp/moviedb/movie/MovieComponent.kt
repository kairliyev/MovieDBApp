package kz.movieapp.moviedb.movie

import dagger.Subcomponent
import kz.movieapp.moviedb.di.AppScope
import kz.movieapp.moviedb.movie.upcoming.UpcomingFragment

@AppScope
@Subcomponent(modules = [(MovieModule::class)])
interface MovieComponent {
//    fun inject(target: NowPlayingFragment)
    fun inject(target: UpcomingFragment)
}