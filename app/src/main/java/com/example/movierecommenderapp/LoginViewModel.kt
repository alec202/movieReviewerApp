package com.example.movierecommenderapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    data class User (
        val id:String="",
        val username:String=""
    )

    val newCheck = MutableLiveData<Int>(0)
    val noEmail = MutableLiveData<String>("")
    val noPass = MutableLiveData<String>("")
    val noUser = MutableLiveData<String>("")
    private val _msg:MutableLiveData<String?> = MutableLiveData(null)
    private val _uid:MutableLiveData<String?> = MutableLiveData(null)
    private val auth = Firebase.auth
    private val db = Firebase.firestore
    val msg: LiveData<String?> get() = _msg
    val uid: LiveData<String?> get() = _uid

    init {
        _uid.postValue(auth.uid)
    }

    fun login(email:String, password:String) {
        viewModelScope.launch(Dispatchers.IO) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    _uid.postValue(it.user?.uid)
                }
                .addOnFailureListener {
                    _msg.postValue(it.message)
                }
        }
    }
    fun newAccount(email:String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    _uid.postValue(it.user?.uid)
                }
                .addOnFailureListener {
                    _msg.postValue(it.message)
                }
        }
    }

    fun createUserData(nick: String){
        viewModelScope.launch(Dispatchers.IO) {
            val newUser = User(id = uid.value.toString(), username = nick)
            db.document("/users/${uid.value}")
                .set(newUser)

        }
    }
}