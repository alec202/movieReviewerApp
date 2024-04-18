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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LiveData
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.MutableStateFlow




class reviewedMoviesActivity : ComponentActivity() {
    val vm: reviewedMoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val showNamePassed = intent.getStringExtra("showName")
//        vm.getmovieInfo("Taken")


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
                        displayFirstOption(apiFetchSuccess)
                        Text("Test")
                        vm.movieName = intent.getStringExtra("title")!!
                        vm.getmovieInfo(vm.movieName)
                    }
                }
            }
        }
    }

    @Composable
    fun DisplayMovieScreen(vm: reviewedMoviesViewModel) {
        var movieFetchSuccess by remember {  }

        // Use LaunchedEffect to observe changes in movieFetchSuccess
        LaunchedEffect(movieFetchSuccess) {
            if (movieFetchSuccess == true) {
                displayFirstOption(vm)
            }
        }

        // Display UI components
        Column {
            Text("Test")
            // Your other UI components here
        }
    }


    @Composable
    fun displayFirstOption(apiFetchResult: Boolean, onSucc: () -> Unit, onFail: () -> Unit){
        if (apiFetchResult) {
            onSucc
            TextField(
                value = """Title: ${vm.top3Results[0].title}
                |Description: ${vm.top3Results[0].overview}
            """.trimMargin(), onValueChange = { }, enabled = false
            )
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