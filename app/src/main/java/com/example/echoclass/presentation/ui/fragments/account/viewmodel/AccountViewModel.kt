package com.example.echoclass.presentation.ui.fragments.account.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.echoclass.data.repository.user.UserRepository
import com.example.echoclass.domain.firebase.authentication.UserAuthentication
import com.example.echoclass.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val userRepository: UserAuthentication
):ViewModel() {

    fun createAccount(user: User,password:String,onSuccess:()->Unit,onFailure: (String) -> Unit){
        userRepository.createUser(user,password,onSuccess,onFailure)
    }

    fun login(email:String,password: String,onSuccess: (User) -> Unit,onFailure: (String) -> Unit){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                userRepository.login(email,password,onSuccess,onFailure)
            }
        }
    }

    fun checkUser(onSuccess: (User) -> Unit){
        userRepository.checkUser(onSuccess)
    }

}