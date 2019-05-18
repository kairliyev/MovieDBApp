package kz.movieapp.moviedb.api

import com.google.gson.annotations.SerializedName
import kz.movieapp.moviedb.models.Movie

data class MovieResponse (
    @SerializedName("results")
    var movies: List<Movie>
)