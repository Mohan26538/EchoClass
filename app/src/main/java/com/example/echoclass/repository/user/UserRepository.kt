package com.example.echoclass.repository.user

import android.util.Log
import com.example.echoclass.firebase.authentication.UserAuthentication
import com.example.echoclass.model.User
import com.example.echoclass.path.USERS_PATH
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class UserRepository():UserAuthentication {
    private val firebaseAuth = Firebase.auth
    private val db = Firebase.firestore



    override fun createUser(user: User, password: String, onSuccess: () -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(user.email,password)
            .addOnSuccessListener{
                it.user?.let { it1 -> user.addId(it1.uid) }
                saveUser(user,onSuccess)

            }

    }

    override fun login(user: User,password: String, onSuccess: () -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(user.email,password)
            .addOnSuccessListener{
                onSuccess()
            }.addOnFailureListener{
                Log.e("login",it.message.toString())
            }
    }

    override fun saveUser(user: User, onSuccess: () -> Unit) {
        db.collection(USERS_PATH).document(user.userId).set(user)
            .addOnSuccessListener{
                onSuccess()
            }.addOnFailureListener{
                Log.e("saveUser",it.message.toString())
            }
    }
}