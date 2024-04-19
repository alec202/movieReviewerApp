# Movie Recommender App
Group project to create movie recommender app for Android devices. It will be implemented using Jetpack Compose and the TMDB API (and our code of course).
This app was created by Jacob, Alec, and Catherine.

# Overview
This is the overview of the project. Here, I will put the overview

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

## Movie/Show Information Screen

Lastly, there is our movie & show info screen. After searching, the user is directed here. You can scroll through the results, which includes a title and description of each listing. After clicking on a specific listing, you can 
add it to your lists, and you can even enter a user rating. Of course, we will focus on the UI elements, since a lot of this is just TMDB API:

![1](https://github.com/alec202/movieReviewerApp/assets/117123349/fd005668-6ff8-4c8a-ac70-01e81c0c9d1b)

Here is the screen layout. Now, I will go over the steps.

# Conclusion

This concludes our tutorial to create our movie selection app! Users can log in or create a new account, browse movies with the TMDB API, as well as add movies to their watch list, favorite movies list, and favorite tv shows list.

Here you can see [our repo](https://github.com/alec202/movieReviewerApp)

There are various different ways to code this, as Jetpack Compose is extremely extensive, and is now the preferred way for developers to format UI in android studio. We use many different elements of compose in
our project here, but there are even more that can be implemented. There are also a variety of Jetpack Compose tutorials online that will help you get started in case you want to start your own app!

# See Also

Here is some great [Jetpack Compose Documentation](https://developer.android.com/develop/ui/compose)

Medium also has some great tutorials, [such as this one](https://medium.com/@WhiteBatCodes/simple-login-page-in-jetpack-compose-9c92af690234)
