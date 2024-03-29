package com.example.movierecommenderapp

// We use this to hold the data of each specific user on Firebase. So for each user we
// will have a specific instance of this object
data class userData(
    val email: String = "empty",
    val password: String = "noPasswordEntered",
    val userID: String = "No User ID",
    var watchedMovies: ArrayList<movieInfo> = arrayListOf()
)