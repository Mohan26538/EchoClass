package com.example.echoclass.data.repository.student

import android.util.Log
import com.example.echoclass.core.path.ANNOUNCEMENT_PATH
import com.example.echoclass.core.path.COURSES_PATH
import com.example.echoclass.domain.firebase.student.StudentOperations
import com.example.echoclass.domain.model.Announcement
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

//    override suspend fun getAllCourses(department: Department,onSuccess:(Courses)->Unit,onFailure:(String)->Unit) {
//
//        db.collection(COURSES_PATH).document(department.toString()).get().addOnSuccessListener {
//            val data = it.toObject(Courses::class.java)!!
//            CoroutineScope(Dispatchers.Main).launch{
//                onSuccess(data)
//            }
//        }.addOnFailureListener {
//            onFailure(it.message.toString())
//        }
//
//    }
//
//    override suspend fun getAllAnnouncements(
//        department: Department,
//        onSuccess: (List<Announcement>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        db.collection(ANNOUNCEMENT_PATH).document(department.toString()).get().addOnSuccessListener {snapShot->
//            var data:MutableList<Announcement> = mutableListOf()
//            if (snapShot.exists()) {
//                val announcementsList = snapShot.get("announcements") as? List<Map<String, Any>>
//                if (announcementsList != null) {
//                    for (announcementMap in announcementsList) {
//                        try {
//                            // Convert each map to an Announcement object
//                            val announcement = Announcement(
//                                name = announcementMap["name"] as? String ?: "",
//                                date = announcementMap["date"] as? String ?: "",
//                                time = announcementMap["time"] as? String ?: "",
//                                place = announcementMap["place"] as? String ?: ""
//                            )
//                            data.add(announcement)
//                        } catch (e: Exception) {
//
//                            onFailure("Error parsing announcement: ${e.message}")
//                        }
//                    }
//                    CoroutineScope(Dispatchers.Main).launch{
//                        onSuccess(data)
//                        Log.e("announcement",data.toString())
//                    }
//                } else {
//                    onFailure("No announcements found.")
//                }
//            } else {
//                onFailure("Document does not exist.")
//            }
//        }
//            .addOnFailureListener { exception ->
//                onFailure("Error fetching announcements: ${exception.message}")
//            }
//    }

    override suspend fun getAllCourses(department: Department,onFailure:(String)->Unit): Courses {
        return try {
            val snapshot = db.collection(COURSES_PATH).document(department.toString()).get().await()
            snapshot.toObject(Courses::class.java) ?: Courses()
        } catch (e: Exception) {
            Log.e("StudentRepository", "Error fetching courses: ${e.message}")
            onFailure(e.message.toString())
            Courses()
        }
    }

    override suspend fun getAllAnnouncements(department: Department, onFailure: (String) -> Unit): List<Announcement> {
        return try {
            val snapshot = db.collection(ANNOUNCEMENT_PATH).document(department.toString()).get().await()
            val data = mutableListOf<Announcement>()
            if (snapshot.exists()) {
                val announcementsList = snapshot.get("announcements") as? List<Map<String, Any>>
                announcementsList?.forEach { announcementMap ->
                    try {
                        val announcement = Announcement(
                            name = announcementMap["name"] as? String ?: "",
                            date = announcementMap["date"] as? String ?: "",
                            time = announcementMap["time"] as? String ?: "",
                            place = announcementMap["place"] as? String ?: ""
                        )
                        data.add(announcement)
                    } catch (e: Exception) {
                        Log.e("StudentRepository", "Error parsing announcement: ${e.message}")
                    }
                }
                data
            } else {
                Log.e("StudentRepository", "No announcements found.")
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("StudentRepository", "Error fetching announcements: ${e.message}")
            onFailure(e.message.toString())
            emptyList()
        }
    }


}