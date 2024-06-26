package com.example.movierecommenderapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movierecommenderapp.ui.theme.MovieRecommenderAppTheme

class MainActivity : ComponentActivity() {
    private val vm: MainActivityViewModel by viewModels()
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val uid = intent?.getStringExtra("uid")
            vm.uid.value = uid
            vm.getUserInfo()
            val watchedMoviesState = remember { mutableStateOf<List<movieInfo>>(emptyList()) }
            val watchedTVState = remember { mutableStateOf<List<movieInfo>>(emptyList()) }
            val favMoviesState = remember { mutableStateOf<List<movieInfo>>(emptyList()) }
            val favTVState = remember { mutableStateOf<List<movieInfo>>(emptyList()) }
            MovieRecommenderAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val username by vm.username.collectAsStateWithLifecycle()

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.LightGray)
                        // center everything horizontally
                        , horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        val destLauncher = rememberLauncherForActivityResult(
                            ActivityResultContracts.StartActivityForResult()){
                            Log.d("movieInMovieInfoInstance", "Inside main after finishing activity")
                            // If the result code is RESULT_OK then we are adding to the watched alreadly list
                            if (it.resultCode == RESULT_OK){
                                it.data?.let {
                                    val movieToAdd = it.getParcelableExtra<movieInfo>("movieInMovieInfoInstance")
                                    if (movieToAdd != null) {
                                        // If the media type is movie we add to the watched Movies list
                                    if (movieToAdd.mediaType == "movie"){
                                        val updatedList = watchedMoviesState.value.toMutableList()
                                        updatedList.add(movieToAdd)
                                        watchedMoviesState.value = updatedList
                                        Log.d(
                                            "movieInMovieInfoInstance",
                                            "${watchedMoviesState.value}"
                                        )
                                    // Media Type is TV so we should add to the watched TV list
                                    } else {
                                        val updatedList = watchedTVState.value.toMutableList()
                                        updatedList.add(movieToAdd)
                                        watchedTVState.value = updatedList
                                    }
                                    }
                                }
                                // If the code isn't RESULT_OK then we aren't adding to the Favorites lazy rows
                            } else{
                                it.data?.let{
                                    val thingToAdd = it.getParcelableExtra<movieInfo>("movieInMovieInfoInstance")
                                    if (thingToAdd != null) {
                                        // If the media type is movie we add to the favorites Movies list
                                        if (thingToAdd.mediaType == "movie"){
                                            val updatedList = favMoviesState.value.toMutableList()
                                            updatedList.add(thingToAdd)
                                            favMoviesState.value = updatedList
                                            // Media Type is TV so we should add to the favorites TV list
                                        } else {
                                            val updatedList = favTVState.value.toMutableList()
                                            updatedList.add(thingToAdd)
                                            favTVState.value = updatedList
                                        }
                                    }

                                }
                            }
                        }
                        val thisContext = LocalContext.current
                        val thisActivity = thisContext as? Activity

                        Spacer(modifier = Modifier.size(15.dp))
                            Greeting(name = username)
                        Spacer(modifier = Modifier.size(15.dp))
                        SearchBar(destLauncher, thisActivity, thisContext)
                        Spacer(modifier = Modifier.size(25.dp))
                        displayWatchedMovies(watchedMoviesState, "Watched Movies")
                        Spacer(modifier = Modifier.size(25.dp))
                        displayWatchedMovies(movieList = watchedTVState, title = "Watched TV")
                        Spacer(modifier = Modifier.size(25.dp))
                        displayWatchedMovies(favMoviesState, "Favorite Movies")
                        Spacer(modifier = Modifier.size(25.dp))
                            displayWatchedMovies(favTVState, "Favorite TV")
                        Spacer(modifier = Modifier.size(25.dp))
                        //buttonToLaunchUserMovieScreen()
                        buttonToLogout()
                    }
                }
            }
        }


    }


    //Collects uid for user after logging in
    override fun onResume() {
        super.onResume()
        vm.uid.value = intent.getStringExtra("uid")
        vm.getUserInfo()
        println(vm.username.value)
    }

    @Composable
    fun displayWatchedMovies(movieList: MutableState<List<movieInfo>>, title: String) {
        Column {
            Text(
                text = title,
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(movieList.value.size) {
                    Text(
                        text = movieList.value[it].name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .width(90.dp)
                            .height(90.dp)
                            .background(Color.White)
                            .border(BorderStroke(2.dp, SolidColor(Color.Black)))
                    )
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello ${vm.username.value}!",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = modifier
        )
    }

    @Composable
    fun SearchBar(
        destLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>,
        thisActivity: Activity?,
        thisContext: Context
    ) {
        var text by remember { mutableStateOf("") }

        Row {
            Spacer(modifier = Modifier.size(15.dp))
            TextField(
                value = text ,
                onValueChange = {text = it},
                label = { Text("Search") },
                modifier = Modifier
                    .width(250.dp)
            )
            Spacer(modifier = Modifier.size(15.dp))
            Button(
                onClick = {
                    val toSearch = Intent(thisContext, reviewedMoviesActivity::class.java)
                    toSearch.putExtra("title", text)
                    destLauncher.launch(toSearch)
                }
            ) {
                Text("Search")

            }
        }
    }

    @Composable
    fun buttonToLaunchUserMovieScreen() {
        Button(
            onClick = {
                val toUserMovies = Intent(this, reviewedMoviesActivity::class.java)
                finish()
                startActivity(toUserMovies)
            }, content = {
                Text("Go to Second screen")
            })

    }

    //Button that allows the user to log out
    @Composable
    fun buttonToLogout() {
        Button(
            onClick = {
                vm.logout()
                val toLogin = Intent(this, LoginActivity::class.java)
                startActivity(toLogin)
                finish()

            }, content = {
                Text("Log Out")
            }
        )
    }


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        val movie1 = movieInfo("Casino Royale", 9.0, "empty", "Tv")
        val movie2 = movieInfo("Quantum of Solace", 6.5, "empty", "tv")
        val movie3 = movieInfo("Skyfall", 7.8, "10.0", "tv")
        val movie4 = movieInfo("Diamonds are Forever", 5.5, "7.8", "tv")
        val movie5 = movieInfo("No Time To Die", 8.8, "7.5", "tv")
        val tv1 = movieInfo("Psych", 8.0, "9.5", "tv")
        val tv2 = movieInfo("House MD", 9.0, "8.6", "tv")
        val tv3 = movieInfo("Leverage",9.6, "10.0", "tv")
        val testArray = arrayListOf(movie1, movie2, movie3,movie4, movie5)
        val array2 = arrayListOf(movie1, movie3)
        val testArray3 = arrayListOf(tv1,tv2,tv3)
        MovieRecommenderAppTheme {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
                // center everything horizontally
                , horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.size(15.dp))
                Greeting(name = "Cathy")
                Spacer(modifier = Modifier.size(15.dp))
//                SearchBar(destLauncher)
                Spacer(modifier = Modifier.size(25.dp))
//                displayWatchedMovies(testArray, "Watched Movies")
                Spacer(modifier = Modifier.size(25.dp))
//                displayWatchedMovies(array2, "Favorite Movies")
                Spacer(modifier = Modifier.size(25.dp))
//                displayWatchedMovies(testArray3, "Favorite TV")
                Spacer(modifier = Modifier.size(25.dp))
                //buttonToLaunchUserMovieScreen()
                buttonToLogout()
            }
        }
    }
}