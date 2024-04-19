package com.example.movierecommenderapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


// This will hold the information about a specific movie this user has seen,
// we'll have an arrayList holding multiple instances of this per user.
// Main idea for this class is because we don't need to store unecessary
// stuff like the poster_path since we won't use it. So when we store
// stuff on firebase it will be this an Array of this class
@Parcelize
data class movieInfo(
    val name: String,
    var userRating: Double,
    val description: String,
    val mediaType: String
    )  : Parcelable