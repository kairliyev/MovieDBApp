package kz.movieapp.moviedb.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_genre.view.*
import kz.movieapp.moviedb.R
import kz.movieapp.moviedb.models.Genre

class GenreAdapter : RecyclerView.Adapter<GenreAdapter.ViewHolder>(){
    private var genres: List<Genre> = ArrayList()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(genres[position])
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val root = (LayoutInflater.from(parent?.context).inflate(R.layout.item_genre, parent, false))
        return ViewHolder(root)
    }

    fun addGenre(genres: List<Genre>?) {
        if (genres != null) {
            this.genres = genres
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(root: View) : RecyclerView.ViewHolder(root) {

        fun bind(genre: Genre) = with(itemView) {
            genre_name?.text = genre.name
        }
    }
}