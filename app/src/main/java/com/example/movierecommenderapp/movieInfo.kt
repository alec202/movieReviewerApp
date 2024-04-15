package com.example.movierecommenderapp

// This will hold the information about a specific movie this user has seen,
// we'll have an arrayList holding multiple instances of this per user.
// Main idea for this class is because we don't need to store unecessary
// stuff like the poster_path since we won't use it. So when we store
// stuff on firebase it will be this an Array of this class
data class movieInfo(
    val name: String,
    var audienceRating: Double,
    var userRating: Double
    )