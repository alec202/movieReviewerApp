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
import androidx.compose.material3.TextField
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData




class reviewedMoviesActivity : ComponentActivity() {
    val vm: reviewedMoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val showNamePassed = intent.getStringExtra("showName")
//        vm.getmovieInfo("Taken")
        val apiSuccess by vm.movieFetchSuccess.observeAsState()


        setContent {
            MovieRecommenderAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Text("Test")
                        vm.movieName = intent.getStringExtra("title")!!
                        vm.getmovieInfo(vm.movieName)
                        vm.movieFetchSuccess.observe(this){

                        }
                    }
                }
            }
        }
    }

    @Composable
    fun displayFirstOption(){
        TextField(
            value = """Title: ${vm.top3Results[0].title}
                |Description: ${vm.top3Results[0].overview}
            """.trimMargin(), onValueChange = { }, enabled = false)
    }


} // end of reviewedMoviesActivity Class


@Preview(showBackground = true)
@Composable
fun displayWatchedMoviesPreview() {
    MovieRecommenderAppTheme {
//        displayWatchedMovies()
    }
}