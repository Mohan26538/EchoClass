package com.example.echoclass.domain.model

enum class UserType(private val userTyper:String) {
    student("Student"),
    Professor("Professor"),
    notSpecified("Not Specified")
}