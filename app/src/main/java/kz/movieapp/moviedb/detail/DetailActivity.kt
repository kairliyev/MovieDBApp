package kz.movieapp.moviedb.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_overview.*
import kotlinx.android.synthetic.main.movie_wrapper.*
import kz.movieapp.moviedb.App
import kz.movieapp.moviedb.BuildConfig
import kz.movieapp.moviedb.R
import kz.movieapp.moviedb.models.MovieDetail
import kz.movieapp.moviedb.models.Videos
import javax.inject.Inject

class DetailActivity : AppCompatActivity(), DetailView {
    @Inject
    lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        (applicationContext as App).createDetailComponent().inject(this)

        val id = intent.getStringExtra("id")
        setupToolbar()
        initLayout()
        presenter.setView(this, id)

//        (this.applicationContext as App).welcoming(this, watch_trailer, "Trailer!", "Tap to watch the movie trailer")
    }

    private fun setupToolbar(){
//        setSupportActionBar(appbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        collapsingToolbar.setExpandedTitleMargin(50, 50, 250, 50)
    }

    override fun showMovieDetails(movies: MovieDetail?) {
        val rateAvg = movies?.voteAverage?.div(2)
        loading.visibility = View.GONE

        overview?.text = movies?.overview
        rate?.text = movies?.voteAverage.toString()
        vote_count?.text = movies?.voteCount.toString()
        release_date?.text = movies?.releaseDate
        runtime?.text = movies?.runtime.toString()
        if (rateAvg != null) {
            rating_bar.rating = rateAvg.toFloat()
        }
        Glide.with(this)
            .load(movies?.getPosterUrl())
            .into(poster)
        (genre_list.adapter as GenreAdapter).addGenre(movies?.genres)
    }

    override fun getVideos(videos: List<Videos>?) {
        val key = videos?.get(0)?.key
        watch_trailer.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.YOUTUBE+key))
            startActivity(intent)
        }
    }

    private fun initLayout() {
        genre_list.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        genre_list.layoutManager = layoutManager
        genre_list.setHasFixedSize(true)
        genre_list.adapter = GenreAdapter()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}