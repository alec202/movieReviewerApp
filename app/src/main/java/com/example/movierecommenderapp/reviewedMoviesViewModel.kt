package com.example.movierecommenderapp

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import edu.gvsu.cis.wordguess.movieAPI
import edu.gvsu.cis.wordguess.movieInfoClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class reviewedMoviesViewModel(): ViewModel() {
    val apiEndpoint: movieAPI
    var movieName: String = ""

    init {
        apiEndpoint = movieInfoClient.getInstance().create(movieAPI::class.java)
    }

//    viewModelScope.launch(Dispatchers.IO) {
//        var returnedMovieInfos = apiEndpoint.get(numWords, maxWordLength)
//        rWords.body().let{
//            if (it != null) {
//                Log.d("Values Returned from API: ", "$it")
//                listOfWord = it as MutableList<String>
//                _wordFetchComplete.postValue(true)
//            }
//        }
//    }

    fun getmovieInfo(movieName: String){
        viewModelScope.launch(Dispatchers.IO) {
            var returnedMovieInfos = apiEndpoint.getMovieInfo(movieName,
                true,
                "en-US",
                1)
            // now need to deconstruct variable for the info returned
            // from the api
            returnedMovieInfos.body().let {
                if (it != null){
                    Log.d("APIReturnedValues", "$it")

                }
            }


        }

    }


}