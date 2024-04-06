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

class MainActivityViewModel: ViewModel() {
    val uid = MutableLiveData<String?>("")
    private val auth = Firebase.auth

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            auth.signOut()
            uid.postValue(null)
        }
    }

}