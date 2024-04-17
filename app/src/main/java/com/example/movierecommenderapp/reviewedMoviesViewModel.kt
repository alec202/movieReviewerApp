package com.example.movierecommenderapp

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import edu.gvsu.cis.wordguess.movieAPI
import edu.gvsu.cis.wordguess.movieInfoClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope



class reviewedMoviesViewModel(): ViewModel() {
    val apiEndpoint: movieAPI
    var movieName: String = ""
    var listOfResults: List<Result> = listOf()
    private val _movieFetchSuccess: MutableLiveData<Boolean?> = MutableLiveData(null)
    val movieFetchSuccess: LiveData<Boolean?> get() = _movieFetchSuccess
    val top3Results: MutableList<Result> = mutableListOf()



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

    fun getmovieInfo(movieName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            var returnedMovieInfos = apiEndpoint.getMovieInfo(
                movieName,
                true,
                "en-US",
                1
            )
            // now need to deconstruct variable for the info returned
            // now need to deconstruct variable for the info returned
            // from the api
            returnedMovieInfos.body().let {
                if (it != null) {
                    listOfResults = it.results
                    Log.d("apiReturnedValues", "${listOfResults.size}")
                    Log.d("apiReturnedValues", "${listOfResults}")
                    Log.d("ApireturnedValues", "${listOfResults[0].title}")
                    top3Results.add(listOfResults[0])
                    top3Results.add(listOfResults[1])
                    top3Results.add(listOfResults[2])
                    // we now populated the top 3 results.
                    // We now have everything we need, so getting the results of the top 3
                    // responses was a success.
                    _movieFetchSuccess.postValue(true)
                }
            }
        }
    }

}