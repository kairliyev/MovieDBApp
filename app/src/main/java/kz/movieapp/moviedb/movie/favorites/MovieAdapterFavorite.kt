package kz.movieapp.moviedb.movie.favorites

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.android.synthetic.main.item_movie_del.view.*

import kz.movieapp.moviedb.detail.DetailActivity
import kz.movieapp.moviedb.models.Movie
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference
import kz.movieapp.moviedb.R


class MovieAdapterFavorite(private val context: Context?) : RecyclerView.Adapter<MovieAdapterFavorite.ViewHolder>(){
    private var movies: ArrayList<Movie> = ArrayList()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val root = (LayoutInflater.from(parent.context).inflate(R.layout.item_movie_del, parent, false))
        return ViewHolder(root)
    }

    fun addMovies(movies: ArrayList<Movie>?) {
        if (movies != null) {
            this.movies = movies
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        var movid  = ""
        init {
            itemView?.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra("id", movies[adapterPosition].id)
                    context?.let { it1 -> startActivity(it1, intent, null) }
                }
            }
            itemView.button_del.setOnClickListener {

                val db = FirebaseDatabase.getInstance().getReference("/movies/").child(movies[adapterPosition].id)
                db.removeValue()
                notifyDataSetChanged()
            }
        }

        fun bind(movie: Movie) = with(itemView) {
            title2.text = movie.title
            Log.d("Adapter",movie.posterPath)
            movid = movie.id
//            Glide.with(context).load(movie.getPosterUrl()).into(poster)
        }
    }
}