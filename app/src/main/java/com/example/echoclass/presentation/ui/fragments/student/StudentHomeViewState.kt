package com.example.echoclass.presentation.ui.fragments.student

import com.example.echoclass.domain.model.Announcement
import com.example.echoclass.domain.model.Courses

sealed class StudentHomeViewState {
    object Loading: StudentHomeViewState()

    data class Loaded(
        val listOfCourses: Courses,
        val listOfAnnouncement: List<Announcement>
    ):StudentHomeViewState()

    data class Error(val error:String):StudentHomeViewState()

}