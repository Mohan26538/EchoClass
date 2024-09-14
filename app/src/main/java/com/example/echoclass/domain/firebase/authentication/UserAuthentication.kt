package com.example.echoclass.domain.firebase.authentication

import com.example.echoclass.domain.model.User
//TODO:2) Mohan -> implement user authentication
interface UserAuthentication {

    fun createUser(user: User, password:String, onSuccess:()->Unit)

    fun login(user: User, password: String, onSuccess:()->Unit)

    fun saveUser(user: User, onSuccess: () -> Unit)


}