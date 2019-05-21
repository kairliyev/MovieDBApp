package kz.movieapp.moviedb.movie.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_upcoming.*
import kz.movieapp.moviedb.App
import kz.movieapp.moviedb.R
import kz.movieapp.moviedb.models.Movie
import kz.movieapp.moviedb.movie.MovieAdapter
import javax.inject.Inject

class FavoriteFragment : Fragment(), FavoriteFragmentView {

    @Inject
    lateinit var presenter: FavoriteFragmentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (context?.applicationContext as App).createMainComponent().inject(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_upcoming, container, false)
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
        val layoutManager = LinearLayoutManager(context)
        list_movie.layoutManager = layoutManager
        list_movie.setHasFixedSize(true)
        list_movie.adapter = MovieAdapterFavorite(context)
    }

    override fun showFavoriteFragmentMovies(movies: List<Movie>) {
        progress_bar.visibility = View.GONE
        (list_movie.adapter as MovieAdapterFavorite).addMovies(movies)
    }
}