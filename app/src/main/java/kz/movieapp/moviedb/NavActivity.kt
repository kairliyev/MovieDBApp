package kz.movieapp.moviedb

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_nav.*
import kotlinx.android.synthetic.main.app_bar_nav.*
import kotlinx.android.synthetic.main.nav_header_nav.*
import kz.movieapp.moviedb.models.User

import kz.movieapp.moviedb.movie.favorites.FavoriteFragment
import kz.movieapp.moviedb.movie.genre.GenreFragment
import kz.movieapp.moviedb.movie.genrefilter.GenreFilter
import kz.movieapp.moviedb.movie.latestmovie.LatestMovie
import kz.movieapp.moviedb.movie.movies.MovieFragment
import kz.movieapp.moviedb.movie.nowplaying.NowPlaying
import kz.movieapp.moviedb.movie.popularmovies.PopularMovies
import kz.movieapp.moviedb.movie.upcoming.UpcomingFragment
import kz.movieapp.moviedb.utils.Language


class NavActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var savedState: Bundle? = null
    var uid: String? = null
    var user: User? = null
    var mUser: User? = null
    var username: String? = ""
    var image: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedState = savedInstanceState

        setContentView(R.layout.activity_nav)
        setSupportActionBar(toolbar_genre)
        loadMoviesFragment(savedInstanceState)
        uid = FirebaseAuth.getInstance().uid
        fetchUsers()


        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar_genre, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
//        handleIntent(intent)
    }

    private fun fetchUsers() {
        val ref = FirebaseDatabase.getInstance().getReference("/users")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {

                p0.children.forEach {
                    user = it.getValue(User::class.java)
                    if (user?.uid == uid) {
                        username = user?.username
                        image = user?.profileImageUrl
                        componentBind(username, image)
                        Log.d("Profile", username)
                    }
                }

            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }

    private fun componentBind(username: String?, image: String?) {
        Glide.with(this).load(image).into(imageView)
        nav_name.text = username
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (Intent.ACTION_SEARCH == intent?.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            searchByGenre(query, savedState)
            Toast.makeText(applicationContext, query, Toast.LENGTH_LONG).show()
        }
    }

    private fun searchByGenre(query: String?, savedState: Bundle?) {
        if (savedState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, GenreFilter("2"), GenreFilter::class.simpleName)
                .commit()
        }

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.nav, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
//            R.id.nav_camera -> {
//                loadUpcomingFragment(savedState)
//            }
//            R.id.nav_gallery -> {
//                loadNowPlayingFragment(savedState)
//            }
//            R.id.nav_slideshow -> {
//                loadPopularMoviesFragment(savedState)
//            }
//            R.id.nav_manage -> {
//                loadLatestMovieFragment(savedState)
//            }
            R.id.movies_item -> {
                loadMoviesFragment(savedState)
            }
            R.id.genre_item -> {
                loadGenreFragment(savedState)
            }


//            R.id.fav -> {
//                loadFavoriteMovieFragment(savedState)
//            }
//            R.id.nav_send -> {
//
//            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun loadGenreFragment(savedState: Bundle?) {
        if (savedState == null) {
            toolbar_genre?.title = "Жанры"
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, GenreFragment(), GenreFragment::class.simpleName)
                .commit()
        }

    }

    private fun loadMoviesFragment(savedState: Bundle?) {
        if (savedState == null) {
            toolbar_genre?.title = "Фильмы"
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, MovieFragment(), MovieFragment::class.simpleName)
                .commit()
        }
    }

    private fun loadFavoriteMovieFragment(savedState: Bundle?) {
        if (savedState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, FavoriteFragment(), FavoriteFragment::class.simpleName)
                .commit()
        }
    }
//    private fun loadPlayingFragment(savedInstanceState: Bundle?) {
//        if (savedInstanceState == null) {
//            supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.main_container, NowPlayingFragment(), NowPlayingFragment::class.simpleName)
//                .commit()
//        }
//    }

    private fun loadUpcomingFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, UpcomingFragment(), UpcomingFragment::class.simpleName)
                .commit()
        }
    }

    private fun loadNowPlayingFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, NowPlaying(), NowPlaying::class.simpleName)
                .commit()
        }
    }

    private fun loadPopularMoviesFragment(savedInstanceState: Bundle?) {

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, PopularMovies(), PopularMovies::class.simpleName)
                .commit()
        }
    }

    private fun loadPopularMoviesFragmentToChangeLanguage(savedInstanceState: Bundle?) {

        if (savedInstanceState == null) {
            Language.language = "ru"
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, PopularMovies(), PopularMovies::class.simpleName)
                .commit()
        }
    }


    private fun loadLatestMovieFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, LatestMovie(), LatestMovie::class.simpleName)
                .commit()
        }
    }
}
