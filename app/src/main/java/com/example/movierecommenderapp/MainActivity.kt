package com.example.movierecommenderapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.movierecommenderapp.ui.theme.MovieRecommenderAppTheme

class MainActivity : ComponentActivity() {
    val vm: MainActivityViewModel by viewModels()
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
                        // center eveyrthing vertically
                        , horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // tell the user
                        BasicTextField(value = "Search for a movie to add", readOnly = true, onValueChange = {}, modifier = Modifier)
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
    fun displayWatchedMovies(movieList: ArrayList<movieInfo>) {
        LazyRow{
            items(movieList.size){
                Text(
                    text = movieList[it].name,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .width(80.dp) // need to test this line and see if it's doing what
                    // we think it's doing
                )
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
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MovieRecommenderAppTheme {
//        Greeting("Android")
    }
}