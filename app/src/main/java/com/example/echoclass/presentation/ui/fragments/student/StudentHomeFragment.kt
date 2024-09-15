package com.example.echoclass.presentation.ui.fragments.student

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.echoclass.R
import com.example.echoclass.databinding.FragmentStudentHomeBinding
import com.example.echoclass.domain.model.Courses
import com.example.echoclass.domain.model.User
import com.example.echoclass.domain.model.options
import com.example.echoclass.presentation.ui.fragments.student.utils.CourseAdapter
import com.example.echoclass.presentation.ui.fragments.student.utils.OptionsAdapter
import com.example.echoclass.presentation.ui.fragments.student.viewmodel.StudentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class StudentHomeFragment : Fragment() {

    private lateinit var studentHomeBinding: FragmentStudentHomeBinding
    private lateinit var user:User
    val studentViewModel:StudentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        studentHomeBinding = FragmentStudentHomeBinding.inflate(inflater,container,false)
        return studentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            user = it.getParcelable<User>("user")?:User()
        }

        Log.e("studentHome",user.toString())

        val optionAdapter = OptionsAdapter(options)
        studentHomeBinding.rvOptions.layoutManager = GridLayoutManager(requireContext(),3)
        studentHomeBinding.rvOptions.adapter = optionAdapter

        studentViewModel.getAllCourses(user.department,::onCourseLoaderFailed)

        studentViewModel.course.observe(viewLifecycleOwner){ courses ->
            val courseAdapter = CourseAdapter(courses)
            studentHomeBinding.rvCourses.layoutManager = GridLayoutManager(requireContext(),2)
            studentHomeBinding.rvCourses.adapter = courseAdapter
            studentHomeBinding.progressBarCourses.visibility = View.GONE
        }


        studentHomeBinding.tvGreeting.text = "Hi, ${user.name}"

    }


    private fun onCourseLoaderFailed(error:String){
        Log.e("courseLoader",error)
    }

}