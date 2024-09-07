package com.example.echoclass.repository.user

import com.example.echoclass.firebase.authentication.UserAuthentication
import com.example.echoclass.model.User

class UserRepository():UserAuthentication {
    override fun createUser(user: User, password: String, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun login(user: User, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun saveUser(user: User, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }
}