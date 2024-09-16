package com.example.echoclass.domain.firebase.authentication

import com.example.echoclass.domain.model.User
//TODO:2) Mohan -> implement user authentication
interface UserAuthentication {

    fun createUser(user: User, password:String, onSuccess:()->Unit,onFailure:(String)->Unit)

    fun login(email:String, password: String, onSuccess:(User)->Unit,onFailure: (String) -> Unit)

    fun saveUser(user: User, onSuccess: () -> Unit,onFailure: (String) -> Unit)

    fun checkUser(onSuccess: (User) -> Unit)

}