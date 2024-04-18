package com.example.movierecommenderapp

import android.os.Bundle
import android.util.Log
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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
//import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
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
                        displayFirstOption(apiFetchSuccess)
                        displaySecondOption(apiFetchResult = apiFetchSuccess)
                        displayThirdOption(apiFetchResult = apiFetchSuccess)
                        displayThirdOption(apiFetchResult = apiFetchSuccess)

                    }
                }
            }
        }
    }

//    @Composable
//    fun DisplayMovieScreen(vm: reviewedMoviesViewModel) {
//        var movieFetchSuccess by remember {  }
//
//        // Use LaunchedEffect to observe changes in movieFetchSuccess
//        LaunchedEffect(movieFetchSuccess) {
//            if (movieFetchSuccess == true) {
//                displayFirstOption(vm)
//            }
//        }
//
//        }
//    }


    @Composable
    fun displayFirstOption(apiFetchResult: Boolean){
        if (apiFetchResult) {
//            onSucc
            val firstOption = vm.top3Results[0]
            if (firstOption.title != null){
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
                TextField(
                    value = """Title: ${vm.top3Results[1].title}
                |Description: ${vm.top3Results[1].overview}
            """.trimMargin(), onValueChange = { }, enabled = false, modifier = Modifier
                        .fillMaxWidth(1f)
                )
            } else {
                TextField(
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
                TextField(
                    value = """Title: ${vm.top3Results[2].title}
                |Description: ${vm.top3Results[2].overview}
            """.trimMargin(), onValueChange = { }, enabled = false, modifier = Modifier
                        .fillMaxWidth(1f)
                        .clickable { Log.d("apiReturnedValues", "ClickedThird ") }
                )
            } else {
                TextField(
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