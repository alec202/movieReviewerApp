package com.example.movierecommenderapp

data class Result(
    val backdrop_path: String?,
    val id: Int,
    val original_name: String?,
    val overview: String,
    val poster_path: String?,
    val media_type: String,
    val adult: Boolean,
    val name: String?,
    val original_language: String,
    val genre_ids: List<Int>,
    val popularity: Double,
    val first_air_date: String?,
    val vote_average: Double,
    val vote_count: Int,
    val origin_country: List<String>?,
    val original_title: String?,
    val title: String?,
    val release_date: String?,
    val video: Boolean?
)
