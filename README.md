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

![image](https://github.com/alec202/movieReviewerApp/assets/117123349/ad027288-442b-4e49-94a2-519e9ac9af50)

'''

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

'''



## Movie Selection UI

# Further Notices

# See Also
