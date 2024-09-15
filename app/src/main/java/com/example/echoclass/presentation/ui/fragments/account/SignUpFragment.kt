package com.example.echoclass.presentation.ui.fragments.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.echoclass.R
import com.example.echoclass.databinding.FragmentSignUpBinding
import com.example.echoclass.domain.model.Department
import com.example.echoclass.domain.model.User
import com.example.echoclass.domain.model.UserType
import com.example.echoclass.presentation.ui.fragments.account.viewmodel.AccountViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private lateinit var signUpBinding:FragmentSignUpBinding
    val userViewModel:AccountViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signUpBinding = FragmentSignUpBinding.inflate(inflater,container,false)
        return signUpBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var items =resources.getStringArray(R.array.user_types)
        var userAdapterItems = ArrayAdapter(requireContext(),R.layout.user_list_items,items)
        val departmentItems = resources.getStringArray(R.array.departments)
        val departmentAdapterItems = ArrayAdapter(requireContext(),R.layout.user_list_items,departmentItems)
        signUpBinding.etType.setAdapter(userAdapterItems)
        signUpBinding.etDept.setAdapter(departmentAdapterItems)
        signUpBinding.submitBtn.setOnClickListener {
            val name = signUpBinding.etUsername.text.toString()
            val email = signUpBinding.etEmail.text.toString().trim()
            val password = signUpBinding.etPassword.text.toString().trim()
            val userType = when(signUpBinding.etType.text.toString()){
                "Student" -> UserType.student
                "Professor" -> UserType.Professor
                else -> {UserType.notSpecified}
            }
            val department = when(signUpBinding.etDept.text.toString()){
                "CSE" -> Department.CSE
                "CSBS" -> Department.CSBS
                else -> {Department.NotKnown}
            }
            val rollNo = signUpBinding.etRollno.text.toString().trim()

            val user = User(name, rollNo, userType, department, email)

            userViewModel.createAccount(user,password,::onSuccess,::onFailure)
        }

        signUpBinding.tvlogin.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }
    }

    private fun onSuccess(){
        Toast.makeText(requireContext(),"Account Created!",Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)

    }

    private fun onFailure(error:String){
        Toast.makeText(requireContext(),error,Toast.LENGTH_SHORT).show()
    }

}