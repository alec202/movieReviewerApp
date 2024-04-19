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
import com.example.movierecommenderapp.ui.theme.MovieRecommenderAppTheme

class MainActivity : ComponentActivity() {
    private val vm: MainActivityViewModel by viewModels()
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //val watchedMovie: ArrayList<movieInfo> = intent.getParcelableExtra("Watched Movie")
            val username = intent?.getStringExtra("username")
            val uid = intent?.getStringExtra("uid")
            val watchedMovies = intent?.getParcelableArrayListExtra("watchedMovies", movieInfo::class.java)
            val favMovies = intent?.getParcelableArrayListExtra("favoriteMovies", movieInfo::class.java)
            val favTV = intent?.getParcelableArrayListExtra("favoriteTV", movieInfo::class.java)
            MovieRecommenderAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
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
                            if (it.resultCode == RESULT_OK){
                                it.data?.let {
                                    val movieToAdd = it.getParcelableExtra<movieInfo>("movieInMovieInfoInstance")
                                    val test = it.getStringExtra("movieInMovieInfoInstanceString")
                                    Log.d("movieInMovieInfoInstance", "$movieToAdd")
                                    if (test != null) {
                                        Log.d("movieInMovieInfoInstanceString", test)
                                    } else{
                                        Log.d("movieInMovieInfoInstanceString", "Test is null")
                                    }
                                }
                            }
                        }
                        val thisContext = LocalContext.current
                        val thisActivity = thisContext as? Activity

                        Spacer(modifier = Modifier.size(15.dp))
                        if (username != null) {
                            Greeting(name = username)
                        }
                        Spacer(modifier = Modifier.size(15.dp))
                        SearchBar(destLauncher, thisActivity, thisContext)
                        Spacer(modifier = Modifier.size(25.dp))
                        if (watchedMovies != null) {
                            displayWatchedMovies(watchedMovies, "Watched Movies")
                        }
                        Spacer(modifier = Modifier.size(25.dp))
                        if (favMovies != null) {
                            displayWatchedMovies(favMovies, "Favorite Movies")
                        }
                        Spacer(modifier = Modifier.size(25.dp))
                        if (favTV != null) {
                            displayWatchedMovies(favTV, "Favorite TV")
                        }
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
    }

    @Composable
    fun displayWatchedMovies(movieList: ArrayList<movieInfo>, title: String) {
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
                items(movieList.size) {
                    Text(
                        text = movieList[it].name,
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
            text = "Hello $name!",
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
                displayWatchedMovies(testArray, "Watched Movies")
                Spacer(modifier = Modifier.size(25.dp))
                displayWatchedMovies(array2, "Favorite Movies")
                Spacer(modifier = Modifier.size(25.dp))
                displayWatchedMovies(testArray3, "Favorite TV")
                Spacer(modifier = Modifier.size(25.dp))
                //buttonToLaunchUserMovieScreen()
                buttonToLogout()
            }
        }
    }
}