package com.example.movierecommenderapp

import android.content.Intent
import android.os.Bundle
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
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.google.android.material.snackbar.Snackbar
import androidx.core.content.ContextCompat.startActivity


class LoginActivity : ComponentActivity() {
    val vm: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Login()
        }
        var email = ""

        vm.uid.observe(this) {
            // When the UID is not null, we transition to the main screen
            it?.let {
                val toMain = Intent(this, MainActivity::class.java)
                toMain.putExtra("uid", vm.uid.value)
                toMain.putExtra("username", "N/A")
                startActivity(toMain)
                finish()
            }
        }

        vm.msg.observe(this) {
            it?.let {
                if (it.length > 0) {
                }
            }
        }
    }


    @Composable
    fun Login() {

        var userEmail by remember {
            mutableStateOf("")
        }
        var userPass by remember {
            mutableStateOf("")
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(90.dp))

            Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo")

            OutlinedTextField(value = userEmail, onValueChange = {
                userEmail = it
                vm.noEmail.value = it

            }, label = {
                Text(text = "Email")
            })

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(value = userPass, onValueChange = {
                userPass = it
                vm.noPass.value = it
            }, label = {
                Text(text = "Password")
            }, visualTransformation = PasswordVisualTransformation())

            Spacer(modifier = Modifier.height(60.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {loginNow()},
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .size(width = 135.dp, height = 50.dp)
                ) {
                    Text(text = "Log In")
                }
                Button(
                    onClick = {createAccount()},
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .size(width = 135.dp, height = 50.dp)
                ) {
                    Text(text = "New Account")
                }
            }


        }
    }

    fun loginNow() {
        if (vm.noEmail.value != "" && vm.noPass.value != ""){
            vm.login(vm.noEmail.value!!, vm.noPass.value!!)
        }
    }

    fun createAccount() {
        val toCreate = Intent(this, NewAccount::class.java)
        startActivity(toCreate)
        finish()
    }

}