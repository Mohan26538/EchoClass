package com.example.echoclass.domain.firebase.student

import com.example.echoclass.domain.model.Courses
import com.example.echoclass.domain.model.Department

interface StudentOperations {

    fun listAllLectures()

    fun listAllLiveClasses()

    fun listAllAnnouncement()

    suspend fun getAllCourses(department: Department,onSuccess:(Courses)->Unit,onFailure:(String)->Unit)

}