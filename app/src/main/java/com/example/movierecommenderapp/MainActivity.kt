package com.example.movierecommenderapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                    ) {
                        Greeting("Android")
                        SearchField()
                        ButtonToLaunchUserMovieScreen()
                        ButtonToLogout()
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
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Composable
    fun ButtonToLaunchUserMovieScreen() {
        Button(
            onClick = {
                val toUserMovies = Intent(this, LoginActivity::class.java)
                startActivity(toUserMovies)
            }, content = {
                Text("Go to Second screen")
            })

    }

    //Button that allows the user to log out
    @Composable
    fun ButtonToLogout() {
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
    @Composable
    fun SearchField(){
        var text by remember { mutableStateOf("") }
        Row {
            TextField(
                value = text,
                onValueChange = {text = it},
                label = {Text("Search")},
                modifier = Modifier.padding(10.dp)
            )
            Button(onClick = { vm.search(text) })
            {
                Text("Search")
            }
        }
    }

    @Composable
    fun WatchedMovies(userData: userData){
        val watchList = userData.watchedMovies
        val thisContext = LocalContext.current
        val thisActivity = thisContext as? Activity
        Column {
            Text(text = "Your Movies")
            if(watchList.isEmpty()){
                Text("Looks like you haven't reviewed anything :(")
            }
            LazyRow{
                    items(watchList.size) {
                        Button( //each button will have the title of the movie on it, clicking the button will take you to movie details
                            onClick = {
                                val toUserMovies = Intent(thisContext, reviewedMoviesActivity::class.java)
                                thisActivity?.startActivity(toUserMovies)
                        }) {
                            Text(text = userData.watchedMovies.toString())
                        }
                    }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        MovieRecommenderAppTheme {
            //SearchField()
            //WatchedMovies()
        }
    }
}


