package com.example.echoclass.presentation.ui.activities

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import com.example.echoclass.R
import com.example.echoclass.domain.model.User
import com.example.echoclass.presentation.ui.fragments.account.viewmodel.AccountViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val userViewModel:AccountViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userViewModel.checkUser(::onSuccess)
    }


    private fun onSuccess(user: User){
        val bundle = Bundle()
        bundle.putParcelable("user",user)
        findNavController(R.id.fragmentContainerView2).navigate(R.id.studentHomeFragment,bundle)
    }
}