package com.example.movierecommenderapp

// IMPORTS
import android.content.Intent
import android.os.Bundle
import android.util.AndroidException
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
//import com.google.android.material.snackbar.Snackbar
import androidx.core.content.ContextCompat.startActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch


// Implements the activity
class LoginActivity : ComponentActivity() {
    val vm: LoginViewModel by viewModels()

    // OnCreate functions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Sets the content of the page as the login composable
        setContent {
            Login()
        }


    }

    // Beginning of Login composable function
    @Composable
    fun Login() {

        // username variable. Uses by remember so Jetpack Compose can keep the variables.
        var userEmail by remember {
            mutableStateOf("")
        }
        // password variable. Uses by remember so Jetpack Compose can keep the variables.
        var userPass by remember {
            mutableStateOf("")
        }

        var userName by remember {
            mutableStateOf("")
        }

        // variable for showing/hiding user password, usual function
        var showPass by remember { mutableStateOf(value = false) }


        // Beginning of composable structure
        Column(
            // makes it so that elements take up entire width, elements are centered horizontally
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            
            // Blankspace
            Spacer(modifier = Modifier.height(90.dp))

            // Our logo. Totally not from a random internet source
            Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo")

            // This is the email text field. Updates userEmail var and viewModel var.
            OutlinedTextField(value = userEmail, onValueChange = {
                userEmail = it
                vm.noEmail.value = it
                userName = userEmail.substringBefore("@", "Username")
                vm.noUser.value = userName

                // Hint text
            }, label = {
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


        }

        // Print message to diagnose why login isn't working
        vm.msg.observe(this) {
            it?.let {
                if (it.length > 0) {
                    println("Unable to login $it")
                }
            }
        }

        // Goes to main page when the uid has changed
        vm.uid.observe(this) {
            // When the UID is not null, we transition to the main screen
            it?.let {
                if (vm.newCheck.value == 1){
                    vm.createUserData(vm.noUser.value!!)
                    vm.newCheck.value = 0
                }
                val toMain = Intent(this, MainActivity::class.java)
                toMain.putExtra("uid", vm.uid.value)
                toMain.putExtra("username", "N/A")
                startActivity(toMain)
                finish()
            }
        }

    }

    // Login function. Ensures no app crash if they are empty
    fun loginNow() {
        if (vm.noEmail.value != "" && vm.noPass.value != "") {
            vm.login(vm.noEmail.value!!, vm.noPass.value!!)
        }
    }

    // Create account function. Ensures no app crash if they are empty
    fun createAccount() {
        if (vm.noEmail.value != "" && vm.noPass.value != "") {
            vm.newAccount(vm.noEmail.value!!, vm.noPass.value!!)
            vm.newCheck.value = 1
        }
    }


}
