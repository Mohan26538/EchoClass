package com.example.echoclass.data.repository.student

import com.example.echoclass.core.path.COURSES_PATH
import com.example.echoclass.domain.firebase.student.StudentOperations
import com.example.echoclass.domain.model.Courses
import com.example.echoclass.domain.model.Department
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StudentRepository @Inject constructor(
    private val firebaseAuth:FirebaseAuth,
    private val db:FirebaseFirestore
): StudentOperations {


    override fun listAllLectures() {
        TODO("Not yet implemented")
    }

    override fun listAllLiveClasses() {
        TODO("Not yet implemented")
    }

    override fun listAllAnnouncement() {
        TODO("Not yet implemented")
    }

    override suspend fun getAllCourses(department: Department,onSuccess:(Courses)->Unit,onFailure:(String)->Unit) {

        db.collection(COURSES_PATH).document(department.toString()).get().addOnSuccessListener {
            val data = it.toObject(Courses::class.java)!!
            CoroutineScope(Dispatchers.Main).launch{
                onSuccess(data)
            }
        }.addOnFailureListener {
            onFailure(it.message.toString())
        }


    }
}