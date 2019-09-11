package kz.movieapp.moviedb.models

data class Company(
    val name: String,
    val logo_path: String
) {
    fun getCompanyLogoUrl(): String {
        return "http://image.tmdb.org/t/p/w500$logo_path"
    }
}
