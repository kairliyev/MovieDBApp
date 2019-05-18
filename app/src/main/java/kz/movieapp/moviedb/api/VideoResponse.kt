package kz.movieapp.moviedb.api

import com.google.gson.annotations.SerializedName
import kz.movieapp.moviedb.models.Videos

data class VideoResponse (
    @SerializedName("results")
    var videos: List<Videos>
)