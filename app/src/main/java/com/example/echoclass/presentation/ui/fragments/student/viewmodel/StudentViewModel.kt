package com.example.echoclass.presentation.ui.fragments.student.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.echoclass.data.repository.student.StudentRepository
import com.example.echoclass.domain.firebase.student.StudentOperations
import com.example.echoclass.domain.model.Courses
import com.example.echoclass.domain.model.Department
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class StudentViewModel @Inject constructor (
    private val studentRepository: StudentOperations
):ViewModel() {

    private val _courses:MutableLiveData<Courses> = MutableLiveData()
    val course:LiveData<Courses> = _courses

    fun getAllCourses(department: Department,onFailure:(String)->Unit={}){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                studentRepository.getAllCourses(department,::onCourseLoaderSuccess,onFailure)
            }
        }
    }

    private fun onCourseLoaderSuccess(courses: Courses){
        _courses.postValue(courses)
    }

}