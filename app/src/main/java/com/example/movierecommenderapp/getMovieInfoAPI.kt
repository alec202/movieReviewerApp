package edu.gvsu.cis.wordguess

import com.example.movierecommenderapp.ApiResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


//
// API to be used: https://random-word-api.herokuapp.com/word

val logInterceptor = HttpLoggingInterceptor()
interface movieAPI{
    @Headers(
        "accept: application/json",
        "Authorization",
        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzY2U4MTFiMjc4NDMxNDAwYmJjMWYzNDhhMWM2ZDZmYyIsInN1YiI6IjY1ZjA1M2ZmMWY3NDhiMDBjNzUwOWVjMSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.PjlOGtLwSRvtnYBDW-MNpZdbfpQ3TfB4cnsrGyTR_U0"
    )

    @GET("search/multi")
    suspend fun getMovieInfo(
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean,
        @Query("language") language: String,
        @Query("page") page: Int,
    ): Response<ApiResponse>
}

object wordClient{
    val BASE_URL = "https://api.themoviedb.org/3/"
    val okHttpClientBuilder = OkHttpClient.Builder()

    fun getInstance(): Retrofit {
        logInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        okHttpClientBuilder.addInterceptor(logInterceptor)
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClientBuilder.build())
            .build()

    }
}
