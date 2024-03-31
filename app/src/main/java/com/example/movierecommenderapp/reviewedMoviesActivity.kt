package com.example.movierecommenderapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.movierecommenderapp.ui.theme.MovieRecommenderAppTheme
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Column


class reviewedMoviesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieRecommenderAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Text("Test")
                    }
                }
            }
        }
    }
}

@Composable
fun displayWatchedMovies(movieList: ArrayList<movieInfo>) {
    LazyRow{
        items(movieList.size){
            Text(
                text = movieList[it].movieName,
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

@Preview(showBackground = true)
@Composable
fun displayWatchedMoviesPreview() {
    MovieRecommenderAppTheme {
//        displayWatchedMovies()
    }
}