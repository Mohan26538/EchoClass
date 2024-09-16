package com.example.echoclass.data.repository.user

import android.util.Log
import com.example.echoclass.domain.firebase.authentication.UserAuthentication
import com.example.echoclass.domain.model.User
import com.example.echoclass.core.path.USERS_PATH
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository(): UserAuthentication {
    private val firebaseAuth = Firebase.auth
    private val db = Firebase.firestore



    override fun createUser(user: User, password: String, onSuccess: () -> Unit,onFailure:(String)->Unit) {
        firebaseAuth.createUserWithEmailAndPassword(user.email,password)
            .addOnSuccessListener{
                val user = it.user?.let { it1 -> user.addId(it1.uid) }

                if (user != null) {
                    saveUser(user,onSuccess,onFailure)
                }
            }.addOnFailureListener {
                onFailure(it.message.toString())
            }

    }

    override fun login(email:String, password: String, onSuccess: (User) -> Unit,onFailure: (String) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener{
                CoroutineScope(Dispatchers.IO).launch {
                    getCurrentUser(it.user?.uid.toString(),onSuccess,onFailure)
                }
            }.addOnFailureListener{
                Log.e("login",it.message.toString())
            }
    }

    override fun saveUser(user: User, onSuccess: () -> Unit,onFailure: (String) -> Unit) {
        db.collection(USERS_PATH).document(user.userId).set(user)
            .addOnSuccessListener{
                onSuccess()
            }.addOnFailureListener{
                Log.e("saveUser","uid = ${user.userId}")
                Log.e("saveUser",it.message.toString())

            }
    }

    override fun checkUser(onSuccess: (User) -> Unit) {
        if (firebaseAuth.currentUser!=null){
            CoroutineScope(Dispatchers.IO).launch {
                getCurrentUser(firebaseAuth.currentUser!!.uid,onSuccess)
            }
        }
    }

    private suspend fun getCurrentUser(userId:String,onSuccess: (User) -> Unit,onFailure: (String) -> Unit={}){
        db.collection(USERS_PATH).document(userId).get().addOnSuccessListener {
            val user = it.toObject(User::class.java)
            if (user != null) {
                onSuccess(user)
            }else{
                onFailure("User Not Found")
            }
        }.addOnFailureListener {
            onFailure(it.message.toString())
        }
    }
}