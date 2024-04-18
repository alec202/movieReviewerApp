package com.example.movierecommenderapp

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.movierecommenderapp.ui.theme.MovieRecommenderAppTheme
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
//import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//import androidx.compose.runtime.collectAsStateWithLifecycle





class reviewedMoviesActivity : ComponentActivity() {
    val vm: reviewedMoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val showNamePassed = intent.getStringExtra("showName")
//        vm.getmovieInfo("Taken")
        vm.movieName = intent.getStringExtra("title")!!
        vm.getmovieInfo(vm.movieName)
        setContent {
            MovieRecommenderAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background

                ) {
                    val apiFetchSuccess by vm.movieFetchSuccess.collectAsStateWithLifecycle()

//                    val apiSuccess by vm.movieFetchSuccess.observeAsState()
                    Column {
//                        displayFirstOption(apiFetchSuccess)
//                        displaySecondOption(apiFetchResult = apiFetchSuccess)
//                        displayThirdOption(apiFetchResult = apiFetchSuccess)
//                        displayThirdOption(apiFetchResult = apiFetchSuccess)
                        // display textbox prompting them to click the option they want
                        Text("Click the option you want to add to a list: ",
                            fontSize = 25.sp,
                            modifier = Modifier
                                .fillMaxWidth(1f)
                        )
                        Spacer(modifier = Modifier.size(25.dp))
                        // Display the recyclerView with top 3 options
                        displayTop3Results(apiFetchResult = apiFetchSuccess)
                        Spacer(modifier = Modifier.size(30.dp))
                        askForRating()
                        Spacer(modifier = Modifier.size(30.dp))
                        askWhichListToAddMovieTo()
                        Spacer(modifier = Modifier.size(30.dp))
                        displayButtons(apiFetchSuccess)
                    }
                }
            }
        }
    }


    @Composable
    fun askWhichListToAddMovieTo(){
        Text(text = "Pick the button for the list you want to add this movie to!",
            fontSize = 25.sp,
            modifier = Modifier.fillMaxWidth(1f))
    }

    @Composable
    fun askForRating() {
        var text by remember { mutableStateOf("") }
        val thisContext = LocalContext.current
        val thisActivity = thisContext as? Activity
        Text(text = "Enter a rating:", modifier = Modifier.fillMaxWidth(1f))
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("User Rating") },
            modifier = Modifier
                .fillMaxWidth(1f)
        )
    }


    fun bundleUpForIntentPassing(moviePicked: Result): movieInfo{
        var movieName = ""
        if (moviePicked.title != null){
            return movieInfo("", 3.3, "")
        } else {
            return movieInfo("", 3.3, "")

        }

    }
    fun packIntoIntentAndFinish(moviePicked: movieInfo){

    }
    @Composable
    fun displayButtons(apiFetchResult: Boolean){
        // If that movie generated no results, then we shouldn't allow them to add it to favorites
        if (apiFetchResult){
            Row(horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth(1f)
            ) {
                Button(onClick = {
                    var movieInMovieInfoFormat = bundleUpForIntentPassing(vm.top3Results[vm.indexPicked])
                    packIntoIntentAndFinish(movieInMovieInfoFormat)
                }) {
                    Text("Already Watched List")
                }
                Button(onClick = {
                    var movieInMovieInfoFormat = bundleUpForIntentPassing(vm.top3Results[vm.indexPicked])
                    packIntoIntentAndFinish(movieInMovieInfoFormat)
                }) {
                    // I'll have to unpack and determine the media type and
                    // send to the correct array.
                    Text("Favorites List")
                }


            }

        } else{
            displayNoResultsButton()
        }
    }

    @Composable
    fun displayNoResultsButton(){
        Button(onClick = {finish()}) {
            Text(text = "Back To Main Screen")
        }
    }
    @Composable
    fun displayTop3Results(apiFetchResult: Boolean){
        if (apiFetchResult) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.40f)
            ) {
                items(3) {
                    var currentOption = vm.top3Results[it]
                    if (currentOption.title != null) {
                        TextField(
                            value = """Title: ${currentOption.title}
                    |Description: ${currentOption.overview}
                """.trimMargin(), onValueChange = { }, enabled = false, modifier = Modifier
                                .fillMaxWidth(1f)
                                .fillMaxHeight(0.40f)
                                .clickable {
                                    vm.indexPicked = it
                                    Log.d("ButtonClickYes", "${vm.indexPicked}")
                                }
                        )
                    } else {
                        TextField(
                            value = """Title: ${currentOption.original_name}
                |Description: ${currentOption.overview}
            """.trimMargin(), onValueChange = { }, enabled = false, modifier = Modifier
                                .fillMaxWidth(1f)
                                .fillMaxHeight(0.40f)
                                .clickable {
                                    vm.indexPicked = it
                                    Log.d("ButtonClickYes", "${vm.indexPicked}")
                                }
                        )
                    }

                }
            }
        }
    }


    @Composable
    fun displayFirstOption(apiFetchResult: Boolean){
        if (apiFetchResult) {
//            onSucc
            val firstOption = vm.top3Results[0]
            if (firstOption.title != null) {
                TextField(
                    value = """Title: ${vm.top3Results[0].title}
                    |Description: ${vm.top3Results[0].overview}
                """.trimMargin(), onValueChange = { }, enabled = false, modifier = Modifier
                        .fillMaxWidth(1f)
                )
            } else {
                TextField(
                    value = """Title: ${vm.top3Results[0].original_name}
                |Description: ${vm.top3Results[0].overview}
            """.trimMargin(), onValueChange = { }, enabled = false, modifier = Modifier
                        .fillMaxWidth(1f)
                )
            }
        }
    }

    @Composable
    fun displaySecondOption(apiFetchResult: Boolean){
        if (apiFetchResult){
            val secondOption = vm.top3Results[1]
            if (secondOption.title != null) {
               return TextField(
                    value = """Title: ${vm.top3Results[1].title}
                |Description: ${vm.top3Results[1].overview}
            """.trimMargin(), onValueChange = { }, enabled = false, modifier = Modifier
                        .fillMaxWidth(1f)
                )
            } else {
                return TextField(
                    value = """Title: ${vm.top3Results[1].original_name}
                |Description: ${vm.top3Results[1].overview}
            """.trimMargin(), onValueChange = { }, enabled = false, modifier = Modifier
                        .fillMaxWidth(1f)
                )

            }
        }
    }

    @Composable
    fun displayThirdOption(apiFetchResult: Boolean){
        if (apiFetchResult){
            val thirdResult = vm.top3Results[2]
            if (thirdResult.title != null) {
                return TextField(
                    value = """Title: ${vm.top3Results[2].title}
                |Description: ${vm.top3Results[2].overview}
            """.trimMargin(), onValueChange = { }, enabled = false, modifier = Modifier
                        .fillMaxWidth(1f)
                        .clickable { Log.d("apiReturnedValues", "ClickedThird ") }
                )
            } else {
                return TextField(
                    value = """Title: ${vm.top3Results[2].original_name}
                |Description: ${vm.top3Results[2].overview}
            """.trimMargin(), onValueChange = { }, enabled = false, modifier = Modifier
                        .fillMaxWidth(1f)
                        .clickable { Log.d("apiReturnedValues", "ClickedThird ") }
                )

            }
        }
    }

} // end of reviewedMoviesActivity Class


@Preview(showBackground = true)
@Composable
fun displayWatchedMoviesPreview() {
    MovieRecommenderAppTheme {
//        displayWatchedMovies()
    }
}