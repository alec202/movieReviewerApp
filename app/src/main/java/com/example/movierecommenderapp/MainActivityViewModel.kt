package com.example.movierecommenderapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {

    val uid = MutableLiveData<String?>("")
    private val auth = Firebase.auth
    private val db = Firebase.firestore
    private var _username =  MutableStateFlow("loading")
    val username  = _username.asStateFlow()


    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            auth.signOut()
            uid.postValue(null)
        }
    }

    fun getUserInfo() {
        db.document("/users/${uid.value}")
            .get()
            .addOnSuccessListener {
                val docObjRefer = it.toObject(userData2::class.java)
                Log.d("firebasePull", "${docObjRefer}")
                if (docObjRefer != null) {
                    _username.value = (docObjRefer.username)
                    Log.d("firebasePull", "${username.value}")
                }
            }
    }

    fun search(title: String){

    }

}