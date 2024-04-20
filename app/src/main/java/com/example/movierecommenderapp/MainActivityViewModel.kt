package com.example.movierecommenderapp

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
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {

    val uid = MutableLiveData<String?>("")
    private val auth = Firebase.auth
    private val db = Firebase.firestore
    val username = MutableLiveData<String>("")

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            auth.signOut()
            uid.postValue(null)
        }
    }

    fun getUser() {
        db.document("/users/${uid.value}")
            .get()
            .addOnSuccessListener {
                val docObjRefer = it.toObject(userData2::class.java)
                username.value = docObjRefer?.username
            }
    }

    fun search(title: String){

    }

}