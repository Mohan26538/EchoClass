package com.example.echoclass.model

enum class UserType(private val userTyper:String) {
    student("Student"),
    Professor("Professor"),
    notSpecified("Not Specified")
}