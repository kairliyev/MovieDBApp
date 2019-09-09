package kz.movieapp.moviedb.movie.nowplaying


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_now_playing.*
import kz.movieapp.moviedb.App

import kz.movieapp.moviedb.R
import kz.movieapp.moviedb.models.Movie
import kz.movieapp.moviedb.movie.MovieAdapter
import kz.movieapp.moviedb.movie.upcoming.UpcomingPresenter
import kz.movieapp.moviedb.movie.upcoming.UpcomingView
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class NowPlaying : Fragment(), NowPlayingView {

    @Inject
    lateinit var presenter: NowPlayingPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (context?.applicationContext as App).createMainComponent().inject(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_now_playing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
        presenter.setView(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (context?.applicationContext as App).releaseMainComponent()
    }


    private fun initLayout() {
        list_movie.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(context, 3)
        list_movie.layoutManager = layoutManager
        list_movie.setHasFixedSize(true)
        list_movie.adapter = MovieAdapter(context)
    }

    override fun showNowPlayingMovies(movies: ArrayList<Movie>?) {
        progress_bar.visibility = View.GONE
        (list_movie.adapter as MovieAdapter).addMovies(movies)
    }
}
