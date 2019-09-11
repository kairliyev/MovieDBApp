package kz.movieapp.moviedb.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.item_movie.*
import kotlinx.coroutines.*

import kz.movieapp.moviedb.App
import kz.movieapp.moviedb.BuildConfig
import kz.movieapp.moviedb.R
import kz.movieapp.moviedb.models.Company
import kz.movieapp.moviedb.models.MovieDetail
import kz.movieapp.moviedb.models.Videos
import java.util.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity(), DetailView {
    @Inject
    lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)
        (applicationContext as App).createDetailComponent().inject(this)

        val id = intent.getStringExtra("id")
        initLayout()
        presenter.setView(this, id)

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
        loadImage(movies?.getPosterUrl(), image_detail)

        (company_list.adapter as CompanyAdapter).addcompany(movies?.companies)

//        add_fav.setOnClickListener {
//            saveMovieToDatabase(movies)
//        }
    }

    private fun loadImage(posterUrl: String?, image_detail: ImageView) {
        runBlocking<Unit> {
            launch {
                val imageFullLoading = async {
                    Glide.with(applicationContext).load(posterUrl).into(image_detail)
                }
                imageFullLoading.await()
            }
        }
    }

    override fun getVideos(videos: List<Videos>?) {
        val key = videos?.get(0)?.key
        youtube_video.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.YOUTUBE+key))
            startActivity(intent)
        }
    }

    private fun initLayout() {
        company_list.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        company_list.layoutManager = layoutManager
        company_list.setHasFixedSize(true)
        company_list.adapter = CompanyAdapter()


    }

    private fun saveMovieToDatabase(movies: MovieDetail?) {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val uuid = UUID.randomUUID()
        val UUIDString = uuid.toString()

        val ref = FirebaseDatabase.getInstance().getReference("/movies/${movies?.id}")


        ref.setValue(movies)
            .addOnSuccessListener {
               Toast.makeText(applicationContext, "Успешно" , Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener {
                Toast.makeText(applicationContext, "Не успешно" , Toast.LENGTH_LONG).show()
            }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}