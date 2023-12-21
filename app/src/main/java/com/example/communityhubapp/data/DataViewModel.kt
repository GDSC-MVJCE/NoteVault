package com.example.communityhubapp.data

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects

class DataViewModel : ViewModel() {
    private val TAG = "DataViewModel"
    private var _posts = mutableStateOf<List<PostModel>>(emptyList())
    val posts: State<List<PostModel>> = _posts


    fun getDataFromFireStore() {
        val db = FirebaseFirestore.getInstance()
        db.collection("posts")
            .get()
            .addOnSuccessListener { result ->
                _posts.value = result.toObjects<PostModel>()
                Log.w(TAG,"Data")
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }
}

