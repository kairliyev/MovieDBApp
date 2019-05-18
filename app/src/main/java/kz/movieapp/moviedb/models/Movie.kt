package kz.movieapp.moviedb.models

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("poster_path") val posterPath: String,
    val id: String,
    val title: String)  {

    fun getPosterUrl(): String
    {
        return "http://image.tmdb.org/t/p/w342$posterPath"
    }
}