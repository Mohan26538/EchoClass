package com.example.echoclass.domain.firebase.student

import com.example.echoclass.domain.model.Announcement
import com.example.echoclass.domain.model.Courses
import com.example.echoclass.domain.model.Department

interface StudentOperations {

    fun listAllLectures()

    fun listAllLiveClasses()

    fun listAllAnnouncement()

    suspend fun getAllCourses(department: Department,onFailure:(String)->Unit):Courses

    suspend fun getAllAnnouncements(department: Department, onFailure: (String) -> Unit):List<Announcement>

}