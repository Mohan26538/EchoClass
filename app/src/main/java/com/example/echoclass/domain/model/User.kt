package com.example.echoclass.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val name:String = "",
    val rollNo:String = "",
    val userType: UserType = UserType.notSpecified,
    val department: Department = Department.NotKnown,
    val email:String = "",
    val userId:String = ""


):Parcelable{
    fun addId(uid:String): User {
        return User(name, rollNo, userType, department, email, uid)
    }
}
