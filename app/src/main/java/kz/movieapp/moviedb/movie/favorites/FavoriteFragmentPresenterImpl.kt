package kz.movieapp.moviedb.movie.favorites

import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kz.movieapp.moviedb.models.Movie


class FavoriteFragmentPresenterImpl( private var view: FavoriteFragmentView?): FavoriteFragmentPresenter {

    override fun setView(FavoriteFragmentView: FavoriteFragmentView) {
        view = FavoriteFragmentView
        getFavoriteFragmentMovies()
    }

    private fun getFavoriteFragmentMovies(){
        val ref = FirebaseDatabase.getInstance().getReference("/movies")

        val favMovieList: ArrayList<Movie> = ArrayList()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                favMovieList.clear()
                for (postSnapshot in snapshot.children) {
                    val movie = postSnapshot.getValue<Movie>(Movie::class.java)
                    favMovieList.add(movie!!)
                }
                view?.showFavoriteFragmentMovies(favMovieList)
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }


}