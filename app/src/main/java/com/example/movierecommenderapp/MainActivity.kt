package com.example.movierecommenderapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
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
import androidx.core.content.ContextCompat.startActivity
import com.example.movierecommenderapp.ui.theme.MovieRecommenderAppTheme

class MainActivity : ComponentActivity() {
    private val vm: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieRecommenderAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .background(Color.Yellow)
                            .fillMaxSize()
                        // center everything vertically
                        , horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // tell the user
                        BasicTextField(
                            value = "Search for a movie to add",
                            readOnly = true,
                            onValueChange = {},
                            modifier = Modifier
                        )
//                        Button(onClick = { /*TODO*/ }, content = Text) {
//
//                        }
                        Greeting("Android")

                        buttonToLaunchUserMovieScreen()
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
                fontWeight = FontWeight.Bold
            )
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
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
            modifier = modifier
        )
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
                Text("Log back out")
            }
        )
    }


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        val movie1 = movieInfo("Casino Royale", 8.0, 9.0)
        val movie2 = movieInfo("Quantum of Solace", 6.5, 5.0)
        val movie3 = movieInfo("Skyfall", 7.8, 10.0)
        val movie4 = movieInfo("Diamonds are Forever", 5.5, 7.8)
        val movie5 = movieInfo("No Time To Die", 8.8, 7.5)
        val testArray = arrayListOf(movie1, movie2, movie3,movie4, movie5)
        val array2 = arrayListOf(movie1, movie3)
        MovieRecommenderAppTheme {
            Column {
                displayWatchedMovies(testArray, "Watched Movies")
                displayWatchedMovies(array2, "Favorite Movies")
            }
        }
    }
}