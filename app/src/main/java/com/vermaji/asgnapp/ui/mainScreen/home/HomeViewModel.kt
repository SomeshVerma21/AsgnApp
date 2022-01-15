package com.vermaji.asgnapp.ui.mainScreen.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    val users:MutableLiveData<MutableList<UserModel>> by lazy {
        MutableLiveData<MutableList<UserModel>>()
    }

    fun loadUesrs(){
        viewModelScope.launch {
            getUsersFromFirebase()
        }
    }

    private fun getUsersFromFirebase()
    {
       val item = mutableListOf<UserModel>()
       firebaseDatabase.reference.child("users").addValueEventListener(object : ValueEventListener{
           override fun onDataChange(snapshot: DataSnapshot) {
               for(data in snapshot.children){
                   val userModel = UserModel(id = data.child("id").value.toString()
                   , name = data.child("name").value.toString(), email = data.child("email").value.toString()
                           , age = data.child("age").value.toString())
                   Log.i("Main",userModel.toString())
                   item.add(userModel)
               }
               users.value = item
           }

           override fun onCancelled(error: DatabaseError) {

           }

       })
    }
}