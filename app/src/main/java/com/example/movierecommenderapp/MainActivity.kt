package com.example.movierecommenderapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.startActivity
import com.example.movierecommenderapp.ui.theme.MovieRecommenderAppTheme

class MainActivity : ComponentActivity() {
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
                        buttonToLaunchUserMovieScreen()

                    }
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
                val toUserMovies = Intent(this, LoginActivity::class.java)
                startActivity(toUserMovies)
            }, content = {
                Text("Go to Second screen")
            })

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MovieRecommenderAppTheme {
//        Greeting("Android")
    }
}