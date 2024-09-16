package com.example.echoclass.presentation.ui.fragments.student.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.echoclass.data.repository.student.StudentRepository
import com.example.echoclass.domain.firebase.student.StudentOperations
import com.example.echoclass.domain.model.Announcement
import com.example.echoclass.domain.model.Courses
import com.example.echoclass.domain.model.Department
import com.example.echoclass.presentation.ui.fragments.student.StudentHomeViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class StudentViewModel @Inject constructor (
    private val studentRepository: StudentOperations
):ViewModel() {

    private val _courses:MutableLiveData<Courses> = MutableLiveData()


    private val _studentHomeViewState:MutableLiveData<StudentHomeViewState> = MutableLiveData()
    val studentHomeViewState:LiveData<StudentHomeViewState> = _studentHomeViewState

    private val _announcements:MutableLiveData<List<Announcement>> = MutableLiveData()



    fun loadData(department: Department,onFailure:(String)->Unit={}){
        _studentHomeViewState.postValue(StudentHomeViewState.Loading)
        viewModelScope.launch {
            try {
                val coursesDeferred = async { studentRepository.getAllCourses(department,onFailure) }
                val announcementsDeferred = async { studentRepository.getAllAnnouncements(department,onFailure) }

                val coursesResult = coursesDeferred.await()
                val announcementsResult = announcementsDeferred.await()

                _courses.postValue(coursesResult)
                _announcements.postValue(announcementsResult)
                _studentHomeViewState.postValue(StudentHomeViewState.Loaded(coursesResult, announcementsResult))
            }catch (e:Exception){
                _studentHomeViewState.postValue(StudentHomeViewState.Error(e.message.toString()?:"Unknown Error"))
            }
        }
    }

}