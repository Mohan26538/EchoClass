package com.example.echoclass.presentation.ui.fragments.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.echoclass.R
import com.example.echoclass.databinding.FragmentSignInBinding
import com.example.echoclass.domain.model.User
import com.example.echoclass.domain.model.UserType
import com.example.echoclass.presentation.ui.fragments.account.viewmodel.AccountViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.sin

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private lateinit var signInBinding: FragmentSignInBinding

    val userViewModel:AccountViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        signInBinding = FragmentSignInBinding.inflate(inflater,container,false)
        return signInBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signInBinding.loginBtn.setOnClickListener {
            val email = signInBinding.etloginEmail.text.toString().trim()
            val password = signInBinding.etloginPassword.text.toString().trim()

            userViewModel.login(email,password,::onSuccess,::onFailure)
        }
    }

    private fun onSuccess(user: User){
        val bundle = Bundle()
        bundle.putParcelable("user",user)
        when(user.userType){
            UserType.student -> { findNavController().navigate(R.id.action_signInFragment_to_studentHomeFragment,bundle )}
            UserType.Professor -> {findNavController().navigate(R.id.action_signInFragment_to_professorHomeFragment)}
            UserType.notSpecified -> onFailure("User Type Error Occurred")
        }
    }

    private fun onFailure(error:String){
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }


}