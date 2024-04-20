# Movie Reviewer App
Group project to create movie reviewer app for Android devices. It will be implemented using Jetpack Compose, Firebase, and the TMDB API (and our code of 
course).
This app was created by Jacob, Alec, and Catherine. Development ended on April 19, 2024.

# Overview
For this project, our focus area is Jetpack Compose. Jetpack compose is the recommended modern toolkit for UI building with Android Studio. In fact, these days, when you go
and try to look up documentation on UI creation, Android will almost always direct you to solutions using Jetpack Compose. XML is a very outdated way of UI creation, and
Jetpack Compose just has so many options, such as live previewing, UI building from straight code, easy functions, and many, many libraries to improve functionality.

Our idea for this app was to have a nice way for users to be exposed to the powers of Jetpack Compose. We wanted to focus on an app concept that is very reliant on
a good user interface. We found a nice, free API for movie searching (TMDB API) and got to work!

Our app has many functions. First of all, users can create accounts and login on the landing page - the login page. After logging in with Firebase auth, the user can search up movies
with a search bar directly in our app. The user can then scroll through results from the API and rate the movies, or even add the movie to their unique lists, such as watched movies,
favorite movies, or favorite shows. Users will have their data saved to firebase as well, keeping results even after logging out!

Here is a video demo of our app!


# Getting started

## Software Environment
For this project, we are implementing our Movie Recommender App using Android Studio Hedgehog | 2023.1.1 Patch 2.

**NOTE: For testing our app, we used emulators with AT LEAST Android 13.0, since it seems that older versions struggle with certain
API things we do. We won't go over any API stuff in this demo, rather focusing on the Jetpack Compose things we created.**

**Dependencies:**

![image](https://github.com/alec202/movieReviewerApp/assets/117123349/3d915105-11f8-406f-979d-2d57d4f7a6b1)

**NOTE: There aren't any separate installations you need for this project. Jetpack Compose is fully supported by Android Studio.
Additionally, some of these dependencies are for things that we won't go over with this demo, such as firebase dependencies**


# Coding Section

## Login UI

In the login section, the user can login through Firebase Authentication, either using an existing login or creating a new account.

Here is the finished UI:

![image](https://github.com/alec202/movieReviewerApp/assets/117123349/ff3aaec2-ab60-462f-a190-5ce771236760)

As you can see, the UI consists of a logo, two text fields, and two buttons. Here, I will show you how to code this with Jetpack Compose.

**Step 1: import libraries**

In the beginning of the app, you need to import the following libraries to create the Jetpack Compose UI:

```kotlin

import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

```


**Step 2: Inside of class, create @Composable function Login**

```kotlin

@Composable
fun Login() {

}

```
@Composable helps indicate that the function is meant for Jetpack Compose, used to create the UI of the app. After this, we will fill this function with the page contents.


**Step 3: Add variables we need to keep user email, password, and password visibility boolean**

```kotlin

// username variable. Uses by remember so Jetpack Compose can keep the variables.
var userEmail by remember {
    mutableStateOf("")
}
// password variable. Uses by remember so Jetpack Compose can keep the variables.
var userPass by remember {
    mutableStateOf("")
}

// variable for showing/hiding user password, usual function
var showPass by remember { mutableStateOf(value = false) }

```

By remember is a helpful way to ensure that the variable stays in your composition, even if it's restructured or breaks. The boolean for showPass will help toggle password visibility.


**Step 4: Begin composable structure with a column**

```kotlin

Column(
    // makes it so that elements take up entire width, elements are centered horizontally
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally
){
}

```

This column structure helps organize all of our UI elements. Before the actual function arguments, in the parentheses we can add certain modifiers.

Here, we ensure that the elements are centered horizontally and are also taking up the entire width of the viewport. All of our elements will go in this function.

**Step 5: Add whitespace to top, logo**

```kotlin

// Blankspace
Spacer(modifier = Modifier.height(90.dp))

// Our logo. Totally not from a random internet source
Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo")

```

To explain this, the spacer is just some custom whitespace we can add. The height is in dp (display pixels). For our logo, we pull an image from the R/drawable directory, using its id to find it and add it to the top of the page.

**Step 6: Add two editable text fields for email and password**

```kotlin

// This is the email text field. Updates userEmail var and viewModel var.
OutlinedTextField(value = userEmail, onValueChange = {
    userEmail = it
    vm.noEmail.value = it

//  Hint text
},  label = {
     Text(text = "Valid Email Address")
})

// More whitespace
Spacer(modifier = Modifier.height(20.dp))

// This is the password text field. Updates userPass var and viewModel var.
OutlinedTextField(value = userPass, onValueChange = {
    userPass = it
    vm.noPass.value = it

// Hint text
}, label = {
    Text(text = "Password - 6+ chars")
}, visualTransformation = if (showPass) {
    VisualTransformation.None
} else {
    PasswordVisualTransformation()
},
    trailingIcon = { //Here is where I add the icon that allows you to show the password
        if (showPass) {
            IconButton(onClick = { showPass = false }) {
                Icon(
                    imageVector = Icons.Filled.Visibility,
                    contentDescription = "hide_password"
                    )
            }
        } else {
            IconButton(
                onClick = { showPass = true }) {
                Icon(
                    imageVector = Icons.Filled.VisibilityOff,
                    contentDescription = "hide_password"
                    )
                }
        }
})

```

This section will take a little explanation.  We first start with the text field. To be able to add text to it, we tie it to the variable we created earlier. We also make it so that these two variables change whenever the entered text changes.
The label is the hint text, where we let the user know they must enter a valid email address.

![image](https://github.com/alec202/movieReviewerApp/assets/117123349/4213f373-708f-418d-a0cf-080099469e91)

Next, we create the password text field, which uses a very similar layout, but also has visibility functionality. As you can see, we set it so that if the boolean is true, we show the password.
If the boolean is false, we hide the password. We use visual transformation to use the built-in password censor for Jetpack Compose, creating asterisks. We also use an icon in the Compose Icon library to toggle this visibility.
This icon is the usual eyeball, with a line through it when the password shouldn't be visible. Clicking it changes the visibility, and here is the field, with password visibility on:

![image](https://github.com/alec202/movieReviewerApp/assets/117123349/ab6095d0-59a8-430d-994d-f300093cf1ed)

With it off:

![image](https://github.com/alec202/movieReviewerApp/assets/117123349/874c061c-a9c6-4999-a645-3283993bb2c5)

Another interesting thing to note is that the hint text embeds itself into the border of the text field while entering text, allowing you to continue to see it even after entering text. Pretty Neat!

**Step 7: Add row of buttons to bottom of screen**

```kotlin

// More blankspace
Spacer(modifier = Modifier.height(60.dp))

// Row at bottom that allows you to choose whether you want to log into an existing account or make a new one.
Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.Center
) {
    Button(
        onClick = { loginNow() },
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .size(width = 135.dp, height = 50.dp)
    ) {
        Text(text = "Log In")
    }
    Button(
        onClick = { createAccount() },
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .size(width = 135.dp, height = 50.dp)
    ) {
        Text(text = "New Account")
    }
}

```

First, we start off with the usual white space. Then, we create this row format, allowing us to put these two buttons in the same vertical height. We again add modifiers to take up the entire width, and also make the
buttons arranged in the center of the screen horizontally.

After this, we make the two buttons. We add padding to both of them, giving them space, and we use custom heights and widths of the buttons with the size modifier. We add our functions for logging on and creating a new account
through firebase, and with this, we are done with the login screen! The final step is to actually set the content as this giant login function we created. We do this in the onCreate function in the beginning of our activity, with a simple statement:

```kotlin

// Sets the content of the page as the login composable
setContent {
    Login()
}

```

And with this we are completely finished with our login. See the "Conclusion" section to see our repo, with all of the code, including firebase and viewModels, to see all of the workings of the login page!





## Main Screen

Next, here is our main screen, featuring the sections where you can save your films, as well as a search bar. The user is directed here right after login. You can scroll through these favorited movies and tv shows, and it's a very nice display
so you know what movies you have already seen or favorited. Here is the UI:

![image](https://github.com/alec202/movieReviewerApp/assets/117123349/2dc6860f-9a69-4b53-b010-5e1fc8bca969)

Now, we will go over the steps for this screen.

**Step 1: imports**
```kotlin

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

```

**Step 2: Composable for displaying watched movies**

```kotlin

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

```

Here, we create a column for the watched movies. This also displays the red text for the watched movies section. We also modify a lot of the colors in this, as well as format the movies when they're actually saved for the user, which
I will end up showing later.

**Step 3: Composable for greeting**

```kotlin

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
    text = "Hello ${vm.username.value}!",
    textAlign = TextAlign.Center,
    fontWeight = FontWeight.Bold,
    modifier = modifier
    )
}

```

Here, we create the greeting at the beginning of the page. It gets the users username and simply creates a hello message. Very simple, and uses things we've already gone over, such as text alignment and bolding the font.

**Step 4: Composable for Search Bar**

```kotlin

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

```

Here, we create a nice row for the search bar. Extremely simple. Just creates essentially a text field. The app takes what the user types in and uses the API to come up with results.

**Step 5: Composable for logout button**

```kotlin

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

```

As you can see yet again, this is extremely simple. It simply creates a logout button that goes back to the login page.

**Step 6: Composable to format all content**

Unlike the login page, we show a different way of creating composables here. Almost like lego bricks, we put everything together in *another* composable, formatting everything in here. You will see familiar functions.

```kotlin

@Composable
fun GreetingPreview() {
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
//               SearchBar(destLauncher)
            Spacer(modifier = Modifier.size(25.dp))
//               displayWatchedMovies(testArray, "Watched Movies")
            Spacer(modifier = Modifier.size(25.dp))
//               displayWatchedMovies(array2, "Favorite Movies")
            Spacer(modifier = Modifier.size(25.dp))
//               displayWatchedMovies(testArray3, "Favorite TV")
            Spacer(modifier = Modifier.size(25.dp))
            //buttonToLaunchUserMovieScreen()
            buttonToLogout()
        }
    }
}

```

Here we use the MovieRecommenderAppTheme, which I will touch on soon. Also, we then set the entire content of the page! We have spacers, then the greeting, then the watched movies, then the favorite movies, then the
favorite tv, and then the logout button. As you can see, composable makes reusing code extremely simple! We even use the displayedWatchedMovies function for more than just watched movies, as you can see.

**Step 7: Set content and add extras**

```kotlin

setContent {
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
                    GreetingPreview()
        }
    }
}

```

Here, we again set the content, using the theme, as well as using the surface to get the theme. We make the entire layout a column, and of course, add the actual content we created right before this.

**Extra: THEMES**

When using Jetpack Compose, a ui.theme folder will be created in your project. Here, you can add certain themes that you can apply to any and all pages. Here is our theme in a "Theme.kt" file:

```kotlin

@Composable
fun MovieRecommenderAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

```

Just to quickly go over this, we do things here such as creating a dark theme if the user's device is in dark mode. We also pick the color scheme, typography, and content. This is a great way to make your app uniform throughout.

FYI, here is the screen when movies or shows are added:

![3](https://github.com/alec202/movieReviewerApp/assets/117123349/4d613979-24db-4fb2-ac2c-c9bd56ff6343)


## Movie/Show Information Screen

Lastly, there is our movie & show info screen. After searching, the user is directed here. You can scroll through the results, which includes a title and description of each listing. After clicking on a specific listing, you can 
add it to your lists, and you can even enter a user rating. Of course, we will focus on the UI elements, since a lot of this is just TMDB API:

![1](https://github.com/alec202/movieReviewerApp/assets/117123349/fd005668-6ff8-4c8a-ac70-01e81c0c9d1b)

Here is the screen layout. Now, I will go over the steps.

**Step 1: imports**
```kotlin

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.movierecommenderapp.ui.theme.MovieRecommenderAppTheme
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

```

**Step 2: Composable function for choosing which list the user wants to add to**

```kotlin

@Composable
fun askWhichListToAddMovieTo(){
    Text(text = "Pick the list you want to add this movie to!",
        fontSize = 25.sp,
        modifier = Modifier.fillMaxWidth(1f))
}

```

Here, we create the nice text label. We make the font large so the user can notice it.

**Step 3: Composable function for ratings, list selection, and buttons**

```kotlin

@Composable
fun askForRatingandShowButtons(apiFetchSuccess: Boolean) {
    var text by remember { mutableStateOf("") }
    Text(text = "Enter a rating out of 10:", modifier = Modifier.fillMaxWidth(1f))
    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("User Rating") },
        modifier = Modifier
            .fillMaxWidth(1f)
    )
    Spacer(modifier = Modifier.size(30.dp))
    askWhichListToAddMovieTo()
    Spacer(modifier = Modifier.size(30.dp))
    displayButtons(apiFetchSuccess, text)

}

```

Here, we use the typical by remember variable to save what rating the user enters in case we recompose. We add the text label again, asking for the user to enter a rating. We give the user a text field to enter this rating, and add some modifiers
to make it look clean. We also implement our function from step 2 here, as well as the displayButton function, which I will show next. 

This brings up an interesting point that we don't have to contain everything in a singular function like the login screen. We can also implement one thing at a time and combine them together like this.

**Step 4: Composable function for buttons**

```kotlin

@Composable
fun displayButtons(apiFetchResult: Boolean, userRating: String){
    // If that movie generated no results, then we shouldn't allow them to add it to favorites
    val thisContext = LocalContext.current
    val thisActivity = thisContext as? Activity
    if (apiFetchResult){
        Row(horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth(1f)
        ) {
            Button(onClick = {

                var movieInMovieInfoFormat = bundleUpForIntentPassing(vm.top3Results[vm.indexPicked], userRating.toDouble())
                packIntoIntentAndFinish(movieInMovieInfoFormat, thisContext, thisActivity)
            }) {
                Text("Already Watched List")
            }
            Button(onClick = {
                var movieInMovieInfoFormat = bundleUpForIntentPassing(vm.top3Results[vm.indexPicked], userRating.toDouble())
                packIntoIntentAndFinish(movieInMovieInfoFormat, thisContext, thisActivity)
            }) {

                Text("Favorites List")
            }


        }

    } else{
        displayNoResultsButton()
    }
}

```

This is a long function, but it's pretty simple. We implement logic so that we cannot add anything to the lists if there aren't any results from the API. Focusing on the UI elements, we create these two buttons in a row, declare the text for the buttons,
implementing them perfectly. Also, if there aren't any API results, we display a new button, which is another function that we will go over in this part.

As an FYI, this is what the screen looks like with no API results:

![2](https://github.com/alec202/movieReviewerApp/assets/117123349/2edf5d6f-5f3d-4ea7-ac45-503546bf605a)

As you can see, we just have that singular button.

**Step 5: Composable function for no API Results**

```kotlin

@Composable
fun displayNoResultsButton(){
    Button(onClick = {finish()}) {
        Text(text = "Back To Main Screen")
    }
}

```

The function I just mentioned here is implemented, and as we said, it's really simple. It just displays that "Back To Main Screen" button, since there aren't any results from the API

**Step 6: Composable function for displaying the top 3 results from the API**

```kotlin

@Composable
fun displayTop3Results(apiFetchResult: Boolean){
    if (apiFetchResult) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.40f)
        ) {
            items(3) {
                var currentOption = vm.top3Results[it]
                if (currentOption.title != null) {
                    TextField(
                        value = """Title: ${currentOption.title}
                |Description: ${currentOption.overview}
            """.trimMargin(), onValueChange = { }, enabled = false, modifier = Modifier
                            .fillMaxWidth(1f)
                            .fillMaxHeight(0.40f)
                            .clickable {
                                 vm.indexPicked = it
                                   Log.d("ButtonClickYes", "${vm.indexPicked}")
                             }
                    )
                } else {
                    TextField(
                        value = """Title: ${currentOption.original_name}
            |Description: ${currentOption.overview}
        """.trimMargin(), onValueChange = { }, enabled = false, modifier = Modifier
                            .fillMaxWidth(1f)
                             .fillMaxHeight(0.40f)
                            .clickable {
                                  vm.indexPicked = it
                                   Log.d("ButtonClickYes", "${vm.indexPicked}")
                             }
                    )
                }

            }
        }
     }
}

```

Another long function, but the explanation is fast again. Lazy Column implements a vertically scrolling menu. We make the contents in this lazy column take up the entire width and fourty percent of the height of the display. 
We then combine Compose and API functions to show the listings' title and description through a text field. If the user clicks on this field, we can select it, for adding to certain lists. As the name suggests,
this displays the top three results from the API, as to not make the lazy column extremely long. 
Again, very simple, using a lot of API functions and very few Compose functions.

**Step 7: Composable function for page theme**

```kotlin

@Composable
fun displayWatchedMoviesPreview() {
    MovieRecommenderAppTheme {
//        displayWatchedMovies()
    }
}

```

Here, we are simply showing the theme of the page as the MovieRecommenderAppTheme. We go over this in the section before. Themes can be extremely helpful for adding consistency to your app!

**Step 8: Set the content of the activity**

```kotlin

setContent {
    MovieRecommenderAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background

        ) {
            Column {
                Text("Click the option you want to add to a list: ",
                    fontSize = 25.sp,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                )
                Spacer(modifier = Modifier.size(25.dp))
                // Display the recyclerView with top 3 options
                displayTop3Results(apiFetchResult = apiFetchSuccess)
                Spacer(modifier = Modifier.size(30.dp))
                askForRatingandShowButtons(apiFetchSuccess)
            }
        }
    }
}

```

Here, we first apply the theme. Then, we use Surface to create a container for the content. In this container, we implement the remaining Composable functions from before, essentially building it all together. We also add an extra line of text,
as well as some spacers. 

This code block right here really highlights just how amazing Jetpack Compose can be. We really built this all like a giant Lego project. We can combine pieces in certain steps, and at the end, we combine everything and put on the finishing touches.


# Conclusion

This concludes our tutorial to create our movie selection app! Users can log in or create a new account, browse movies with the TMDB API, as well as add movies to their watch list, favorite movies list, and favorite tv shows list.

Here you can see [our repo](https://github.com/alec202/movieReviewerApp)

There are various different ways to code this, as Jetpack Compose is extremely extensive, and is now the preferred way for developers to format UI in android studio. We use many different elements of compose in
our project here, but there are even more that can be implemented. There are also a variety of Jetpack Compose tutorials online that will help you get started in case you want to start your own app!

# See Also

Here is some great [Jetpack Compose Documentation](https://developer.android.com/develop/ui/compose)

Medium also has some great tutorials, [such as this one](https://medium.com/@WhiteBatCodes/simple-login-page-in-jetpack-compose-9c92af690234)

Another Medium article that was very helpful for understanding [how to use your ViewModel in Jetpack Compose to Update your UI Using Flow](https://medium.com/@chiragthummar16/jetpack-compose-viewmodel-update-ui-using-flow-9ea7870a3072), since mutable live data won't work.

Additionally, this youtuber, [Phillip Lackner](https://www.youtube.com/@PhilippLackner), has some great Jetpack Compose tutorials as well as everything Android. 
[Click here](https://www.youtube.com/watch?v=6_wK_Ud8--0&t=1663s) for a link to a video he made for a quick overview of Jetpack Compose.
[Click here](https://www.youtube.com/watch?v=cDabx3SjuOY&list=PLQkwcJG4YTCSpJ2NLhDTHhi6XBNfk9WiC) for a playlist he made where he goes over all things Jetpack Compose with detail
