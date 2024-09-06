package com.example.echoclass.model

data class User(
    val name:String = "",
    val rollNo:String = "" ,
    val userType: UserType = UserType.notSpecified,
    val department: Department = Department.NotKnown,
    val email:String = "",
    val userId:String = ""
)
