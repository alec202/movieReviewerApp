package com.example.movierecommenderapp

// This will hold the information about a specific movie this user has seen,
// we'll have an arrayList holding multiple instances of this per user.
data class movieInfo(var movieName:String,
                     var ratingOutOf10:Float)