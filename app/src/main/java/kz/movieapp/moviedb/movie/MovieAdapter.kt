package kz.movieapp.moviedb.movie

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*
import kz.movieapp.moviedb.R
import kz.movieapp.moviedb.detail.DetailActivity
import kz.movieapp.moviedb.models.Movie

class MovieAdapter(private val context: Context?) : RecyclerView.Adapter<MovieAdapter.ViewHolder>(){
    private var movies: List<Movie> = ArrayList()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val root = (LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))
        return ViewHolder(root)
    }

    fun addMovies(movies: List<Movie>?) {
        if (movies != null) {
            this.movies = movies
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        init {
            itemView?.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra("id", movies[adapterPosition].id)
                    context?.let { it1 -> startActivity(it1, intent, null) }
                }
            }
        }

        fun bind(movie: Movie) = with(itemView) {
            title.text = movie.title
            Glide.with(context).load(movie.getPosterUrl()).into(poster)
        }
    }
}